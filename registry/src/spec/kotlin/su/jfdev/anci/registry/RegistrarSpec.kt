@file:Suppress("UNUSED_VARIABLE")

package su.jfdev.anci.registry

import com.nhaarman.mockito_kotlin.*
import su.jfdev.anci.registry.exceptions.*
import su.jfdev.anci.registry.registrar.*
import su.jfdev.anci.registry.registrar.RegistryEvent.Reason.*
import su.jfdev.anci.registry.service.*
import su.jfdev.anci.util.*
import su.jfdev.test.calling.*
import su.jfdev.test.calling.Inspector.Inspectors.always
import su.jfdev.test.features.*
import su.jfdev.test.fluent.*
import su.jfdev.test.history.*
import su.jfdev.test.immutability.SpecAdapter.Companion.specAdapter
import su.jfdev.test.immutability.util.*
import java.util.*
import kotlin.test.*

abstract class RegistrarSpec(val service: RegistryService): Stepwise.Spec() {
    val adapter = specAdapter
    private val immutability = Immutabilities.registrar({ UUID.randomUUID().toString() }, ::Sample)

    init {
        "Custom adapter" - {
            "when register source" - {
                "when validate source" - {
                    val adapter = RegistrarAdapter<String, Sample> {
                        object: SampleHandler(it) {
                            override fun validate(source: String) = null != orNull {
                                UUID.fromString(source)
                            }
                        }
                    }
                    "should not use invalid(source) when valid" {
                        val (uuid, source, registrar) = adapter.session()
                        val result = registrar register source ?: fail("result is null")
                        result.uuid shouldEqual uuid
                    }
                    "when invalid" - {
                        val registrar = adapter.session().registrar
                        "should throw RegistrationInvalidException" {
                            assertFailsWith(RegistrationInvalidException::class) {
                                registrar register "invalid"
                            }
                        }
                    }
                }
                "when validate source with custom invalid()" - {
                    val replacement = UUID.randomUUID()
                    val adapter = RegistrarAdapter<String, Sample> {
                        object: SampleHandler(it) {
                            override fun validate(source: String) = null != orNull {
                                UUID.fromString(source)
                            }

                            override fun invalid(source: String) = replacement.toString()
                        }
                    }
                    "should not use invalid(source) when valid" {
                        val (uuid, source, registrar) = adapter.session()
                        val result = registrar register source ?: fail("result is null")
                        result.uuid shouldEqual uuid
                    }
                    "when invalid" - {
                        val registrar = adapter.session().registrar
                        val result = registrar register "invalid" ?: fail("result is null")
                        "should use invalid(source)" {
                            result.uuid shouldEqual replacement
                        }
                        "registrar should not contain invalid source" {
                            none {
                                registrar.sources should haveKey("invalid")
                            }
                        }
                    }
                }
                "when validate registration" - {
                    val replacement = Sample()
                    val invalid = ArrayList<UUID>()
                    val adapter = RegistrarAdapter<String, Sample> {
                        object: SampleHandler(it) {
                            override fun validate(registration: Sample) = registration.uuid !in invalid
                        }
                    }
                    "should not use invalid(reg) when valid" {
                        val (uuid, source, registrar) = adapter.session()

                        val result = registrar register source ?: fail("result is null")
                        result `should not equal` replacement
                    }
                    "when invalid" - {
                        val (uuid, source, registrar) = adapter.session()
                        invalid += uuid
                        "should throw RegistrationInvalidException" {
                            assertFailsWith(RegistrationInvalidException::class) {
                                registrar register source
                            }
                        }
                    }
                }
                "when validate registration with custom invalid()" - {
                    val replacement = Sample()
                    val invalid = ArrayList<UUID>()
                    val adapter = RegistrarAdapter<String, Sample> {
                        object: SampleHandler(it) {
                            override fun validate(registration: Sample) = registration.uuid !in invalid
                            override fun invalid(registration: Sample) = replacement
                        }
                    }
                    "should not use invalid(reg) when valid" {
                        val (uuid, source, registrar) = adapter.session()

                        val result = registrar register source ?: fail("result is null")
                        result `should not equal` replacement
                    }
                    "when invalid" - {
                        val (uuid, source, registrar) = adapter.session()
                        invalid += uuid
                        val result = registrar register source ?: fail("result is null")
                        "should use invalid(reg)" {
                            result == replacement
                        }
                        "should not contain invalid source in sources" {
                            source in registrar.sources
                        }
                        "should not contain received reg in registry" {
                            result in registrar.registry
                        }
                    }
                }
                "when source already used" - {
                    "should call conflict(source)" {
                        val conflict = Sample()
                        val adapter = RegistrarAdapter<String, Sample> {
                            object: SampleHandler(it) {
                                override fun conflict(source: String) = conflict
                            }
                        }
                        val (uuid, source, registrar) = adapter.session()
                        registrar register source `should not be` conflict
                        registrar register source shouldBe conflict
                    }
                }
            }
            "when unregister registration" - {
                "when registration's uuid used" - {
                    "by given registration" - {
                        "should call unregister(registration)" {
                            CallingInspection(always) {
                                RegistrarAdapter<String, Sample> {
                                    object: SampleHandler(it) {
                                        override fun unregister(registration: Sample) = call()
                                    }
                                }
                            } by {
                                val (uuid, source, registrar) = session()
                                val registration = registrar register source
                                registrar unregister registration!!
                            }
                        }
                    }
                    "by other registration" - {
                        "should call conflict(registration)" {
                            CallingInspection(always) {
                                RegistrarAdapter<String, Sample> {
                                    object: SampleHandler(it) {
                                        override fun conflict(registration: Sample) = call()
                                    }
                                }
                            } by {
                                val (uuid, source, registrar) = session()
                                registrar register source
                                registrar unregister Sample(uuid, registrar.registry)
                            }
                        }
                    }
                }
            }

        }
        stepwise("Maps and registry transforming")
        {
            val (uuid, source, registrar) = SampleAdapter.session()
            should("have empty registry") {
                registrar.registry.isEmpty
            }
            should("have empty sources") {
                registrar.sources.isEmpty()
            }
            take("registered source") {
                requireNotNull(registrar register source)
            } then {
                should("contain uuid in registry") {
                    uuid in registrar.registry
                }
                should("contain registration in registry") {
                    result in registrar.registry
                }
                should("contain source in sources") {
                    source in registrar.sources
                }
                after("unregister registration") {
                    registrar unregister result
                }
                shouldNot("contain uuid in registry") {
                    uuid in registrar.registry
                }
                shouldNot("contain registration in registry") {
                    result in registrar.registry
                }
                shouldNot("contain source in sources") {
                    source in registrar.sources
                }
            }
        }
        stepwise("Event handling")
        {
            val (uuid, source, registrar) = SampleAdapter.session()
            val history = take("event listener") {
                EventHistory<RegistryEvent> { RegistryEvent register it }
            }
            take("registered source") {
                requireNotNull(registrar register source)
            } then {
                test("listener should catch event") {
                    history.result.single {
                        it.registration == result && it.phase == Register
                    }
                }
                after("unregister registration") {
                    registrar unregister result
                }
                test("listener should catch event") {
                    history.result.single {
                        it.registration == result && it.phase == Unregister
                    }
                }
            }
        }
        stepwise("Unregister fails")
        {
            val (uuid, source, registrar) = SampleAdapter.session()
            take("registered source") {
                requireNotNull(registrar register source)
            } then {
                catch(RegistrationConflictException::class, "try register source (same)") {
                    registrar register source
                }
                catch(RegistrationConflictException::class, "unregister other registration with same UUID") {
                    registrar unregister Sample(uuid = uuid, registry = registrar.registry)
                }
                checking("unregister registration") {
                    registrar unregister result
                }
                shouldNot("unregister same registration") {
                    registrar unregister result
                }
            }

        }
        "Immutabilities" -
        {
            immutability(adapter) {
                service.registrar(SampleAdapter)
            }
        }
    }

    data class Session(val uuid: UUID, val source: String, val registrar: Registrar<String, Sample>)

    fun RegistrarAdapter<String, Sample>.session(uuid: UUID = UUID.randomUUID())
            = Session(uuid = uuid, source = uuid.toString(), registrar = service.registrar(this))

    override val oneInstancePerTest: Boolean get() = true
    val anyRegistry: Registry<Sample> get() = any()
}