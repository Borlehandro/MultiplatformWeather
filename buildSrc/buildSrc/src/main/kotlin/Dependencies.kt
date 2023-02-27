object Dependencies {

    object Kotlin {
        private const val version = "1.7.20"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

        private const val datetimeVersion = "0.4.0"
        const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:$datetimeVersion"

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

        private const val lifecycleVersion = "2.5.1"

        const val gradlePlugin = "com.android.tools.build:gradle:7.1.3"
        const val composeActivity = "androidx.activity:activity-compose:1.5.1"
        const val viewModelCompose =
            "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion"

        object Compose {
            private const val version = "1.3.1"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val ui = "androidx.compose.ui:ui:$version"
            const val material = "androidx.compose.material:material:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val icons = "androidx.compose.material:material-icons-core:$version"
        }
    }

    object Koin {

        private const val version = "3.2.0"

        const val core = "io.insert-koin:koin-core:$version"
        const val test = "io.insert-koin:koin-test:$version"
        const val android = "io.insert-koin:koin-android:$version"
        const val compose = "io.insert-koin:koin-androidx-compose:$version"
    }

    object Google {

        const val locationService = "com.google.android.gms:play-services-location:21.0.1"
    }

    object Ktor {

        private const val version = "2.2.3"

        const val core = "io.ktor:ktor-client-core:$version"
        const val negotiation = "io.ktor:ktor-client-content-negotiation:$version"
        const val serialization = "io.ktor:ktor-serialization-kotlinx-json:$version"
        const val logging = "io.ktor:ktor-client-logging:$version"

        const val android = "io.ktor:ktor-client-android:$version"
        const val darwin = "io.ktor:ktor-client-darwin:$version"
    }

    object Moko {
        object ViewModel {

            private const val version = "0.15.0"

            const val core = "dev.icerock.moko:mvvm-core:$version"
            const val flow = "dev.icerock.moko:mvvm-flow:$version"
        }

        object Kswift {

            const val version = "0.6.1"
            const val plugin = "dev.icerock.moko.kswift"
        }
    }

    object NapierLogger {

        private const val version = "2.6.1"

        const val logging = "io.github.aakira:napier:$version"
    }
}
