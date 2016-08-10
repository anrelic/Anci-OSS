package su.jfdev.anci.service

import kotlin.annotation.AnnotationRetention.*
import kotlin.annotation.AnnotationTarget.*

/**
 * Mark class as Service (visual only)
 * @param implementations - source set names, which can be used as default
 */
@Retention(SOURCE)
@Target(CLASS)
annotation class Service(val implementations: Array<String> = arrayOf())