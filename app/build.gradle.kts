plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlinAndroidKsp)
    alias(libs.plugins.hiltAndroid)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.picvault"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.picvault"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.picvault.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}


dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Jetpack Compose
    implementation (libs.ui)
    implementation (libs.androidx.material)
    implementation (libs.ui.tooling.preview)

    // CameraX for camera functionality
    implementation (libs.androidx.camera.camera2)
    implementation (libs.androidx.camera.lifecycle)
    implementation (libs.androidx.camera.view)
    implementation (libs.androidx.camera.extensions)

    // Room for local database
    implementation (libs.androidx.room.runtime)
   // kapt (libs.androidx.room.compiler)

    // MediaStore API to save to gallery
    implementation (libs.androidx.core.ktx)

    // Coil for image loading
    implementation (libs.coil.compose)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    // For Robolectric tests.
    testImplementation (libs.hilt.android.testing)
    // ...with Kotlin.
    kaptTest (libs.com.google.dagger.hilt.android.compiler2)
    // ...with Java.
   // testAnnotationProcessor (libs.com.google.dagger.hilt.android.compiler2)

    // For instrumented tests.
    androidTestImplementation (libs.hilt.android.testing)
    // ...with Kotlin.
    kaptAndroidTest (libs.com.google.dagger.hilt.android.compiler2)
    // ...with Java.
    androidTestAnnotationProcessor (libs.com.google.dagger.hilt.android.compiler2)

    // AndroidX Core Testing for LiveData testing
    testImplementation(libs.core.testing)
    androidTestImplementation(libs.core.testing)

    // google truth
    testImplementation(libs.truth)
    androidTestImplementation(libs.truth)

}