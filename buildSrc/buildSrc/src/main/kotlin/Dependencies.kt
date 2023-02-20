object Dependencies {

    object Kotlin {
        private const val version = "1.8.0"
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

        const val gradlePlugin = "com.android.tools.build:gradle:7.4.0"
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

    object Kodein {

        private const val version = "7.18.0"

        const val di = "org.kodein.di:kodein-di:$version"
        const val androidCommon = "org.kodein.di:kodein-di-framework-android-x:$version"
        const val androidViewModel =
            "org.kodein.di:kodein-di-framework-android-x-viewmodel:$version"
        const val compose = "org.kodein.di:kodein-di-framework-compose:$version"
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

    object ViewModel {

        private const val version = "0.15.0"

        const val core = "dev.icerock.moko:mvvm-core:$version"
        const val flow = "dev.icerock.moko:mvvm-flow:$version"
    }
}
