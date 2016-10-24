@file:Suppress("unused")

package su.jfdev.anci.registry

import com.nhaarman.mockito_kotlin.*
import su.jfdev.anci.*
import su.jfdev.anci.registry.registrar.*
import su.jfdev.test.immutability.*
import su.jfdev.test.immutability.util.*


inline fun <reified R: Identified> MockImmutabilities.registry() = Immutabilities.registry { mock<R>() }
fun <R: Identified> Immutabilities.registry(create: () -> R): Immutability<Registry<R>> = SuiteBuilder.build("registry") {
    append(iterable)
    cannot(Immutabilities.set(create), "values") {
        values
    }
}

fun <F, R: Registration<R>> Immutabilities.registrar(fromGen: () -> F, create: () -> R): Immutability<Registrar<F, R>> = SuiteBuilder.build("registrar") {
    cannot(Immutabilities.registry(create), "registry") {
        registry
    }
    cannot(Immutabilities.map(key = fromGen, value = create), "sources") {
        sources
    }
}