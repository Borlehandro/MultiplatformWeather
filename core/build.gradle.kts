plugins {
    kotlin("native.cocoapods")
    id("multiplatform-setup")
    kotlin("plugin.serialization")
    id(Dependencies.Moko.Kswift.plugin) version Dependencies.Moko.Kswift.version
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Core module of Kotlin Multiplatform Mobile Weather Application"
        authors = "Alexey Borzikov"
        homepage = "https://gitlab.decentury.io/multiplatform-weather/weather-app/"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "MultiPlatformLibrary"
            isStatic = false

            export(Dependencies.Moko.ViewModel.core)
            export(Dependencies.Moko.ViewModel.flow)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.Kotlin.Coroutines.core)
                implementation(Dependencies.Kotlin.Serialization.serialization)
                implementation(Dependencies.Kotlin.datetime)

                implementation(Dependencies.Koin.core)

                implementation(Dependencies.Ktor.core)
                implementation(Dependencies.Ktor.negotiation)
                implementation(Dependencies.Ktor.serialization)
                implementation(Dependencies.Ktor.logging)

                api(Dependencies.Moko.ViewModel.core)
                api(Dependencies.Moko.ViewModel.flow)

                implementation(Dependencies.NapierLogger.logging)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(Dependencies.Koin.test)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Dependencies.Ktor.android)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation(Dependencies.Ktor.darwin)
            }
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "io.decentury.mutliplatform.weatherkmm"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

kswift {
    install(dev.icerock.moko.kswift.plugin.feature.SealedToSwiftEnumFeature)
}
