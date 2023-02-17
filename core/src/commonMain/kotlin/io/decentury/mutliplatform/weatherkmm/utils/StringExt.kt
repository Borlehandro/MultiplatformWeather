package io.decentury.mutliplatform.weatherkmm.utils

fun String.toFirstCapital() =
    lowercase()
        .replaceFirstChar {
            if (it.isLowerCase()) it.titlecase() else it.toString()
        }
