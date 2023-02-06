package io.decentury.mutliplatform.weatherkmm.android.common.util

import android.util.Log
import kotlin.properties.ReadOnlyProperty

private const val DEFAULT_TAG = "UNTAGGED"

@Suppress("Unused")
fun classLogger() = ReadOnlyProperty<Any, Logger> { thisRef, _ ->
    Logger(thisRef::class.simpleName ?: DEFAULT_TAG)
}

// TODO: Make this logger cross-platform
@Suppress("Unused")
class Logger(
    private val tag: String,
) {

    fun v(message: String) {
        Log.v(tag, message)
    }

    fun d(message: String) {
        Log.d(tag, message)
    }

    fun i(message: String) {
        Log.i(tag, message)
    }

    fun w(message: String) {
        Log.w(tag, message)
    }

    fun w(e: Exception) {
        Log.w(tag, e)
    }

    fun w(message: String, e: Exception) {
        Log.w(tag, message, e)
    }

    fun e(message: String) {
        Log.e(tag, message)
    }

    fun e(message: String, e: Exception) {
        Log.e(tag, message, e)
    }
}
