object Dependencies {

    object Kotlin {
        private const val version = "1.8.0"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

        object Serialization {
            const val gradlePlugin = "org.jetbrains.kotlin:kotlin-serialization:1.6.21"
            const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.0"
        }

        object Coroutines {
            private const val version = "1.6.4"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        }
    }

    object Android {
        const val gradlePlugin = "com.android.tools.build:gradle:7.4.0"
        const val composeActivity = "androidx.activity:activity-compose:1.5.1"

        object Compose {
            private const val version = "1.3.1"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val ui = "androidx.compose.ui:ui:$version"
            const val material = "androidx.compose.material:material:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val icons = "androidx.compose.material:material-icons-core:$version"
        }
    }
}