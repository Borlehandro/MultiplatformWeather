plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "io.decentury.mutliplatform.weatherkmm.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "io.decentury.mutliplatform.weatherkmm.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":core"))

    implementation(Dependencies.Kotlin.datetime)

    implementation(Dependencies.Android.composeActivity)
    implementation(Dependencies.Android.viewModelCompose)

    implementation(Dependencies.Android.Compose.runtime)
    implementation(Dependencies.Android.Compose.material)
    implementation(Dependencies.Android.Compose.ui)
    implementation(Dependencies.Android.Compose.icons)
    implementation(Dependencies.Android.Compose.tooling)

    implementation(Dependencies.Koin.android)
    implementation(Dependencies.Koin.compose)

    implementation(Dependencies.Google.locationService)
}
