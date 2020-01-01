/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    id("com.android.application")
    id("io.fabric")
    kotlin("android")
    kotlin("kapt")
    id("org.jetbrains.kotlin.android.extensions")
    id("com.google.gms.google-services")
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    defaultConfig {
        applicationId = "com.google.samples.apps.iosched"
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode = Versions.versionCodeMobile
        versionName = Versions.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        /*
            buildConfigField("float[]",
                    "MAP_FLOORPLAN_MAPPING",
                    "{${map_floorplan_nw}, ${map_floorplan_ne}, ${map_floorplan_se}})"

            buildConfigField("com.google.android.gms.maps.model.LatLng",
                    "MAP_VIEWPORT_BOUND_NW",
                    "new com.google.android.gms.maps.model.LatLng(${map_viewport_bound_nw})"
            buildConfigField "com.google.android.gms.maps.model.LatLng",
                    "MAP_VIEWPORT_BOUND_SE",
                    "new com.google.android.gms.maps.model.LatLng(${map_viewport_bound_se})"

            buildConfigField "float", "MAP_CAMERA_FOCUS_ZOOM", "${map_camera_focus_zoom}"
        */

        resValue("dimen", "map_camera_bearing", MapDefaults.map_default_camera_bearing)
        resValue("dimen", "map_camera_target_lat", MapDefaults.map_default_camera_target_lat)
        resValue("dimen", "map_camera_target_lng", MapDefaults.map_default_camera_target_lng)
        resValue("dimen", "map_camera_tilt", MapDefaults.map_default_camera_tilt)
        resValue("dimen", "map_camera_zoom", MapDefaults.map_default_camera_zoom)
        resValue("dimen", "map_viewport_min_zoom", MapDefaults.map_viewport_min_zoom)

        //manifestPlaceholders = [crashlyticsEnabled: true]

    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            manifestPlaceholders = mapOf(Pair("crashlyticsEnabled", true))
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            resValue("string",
                    "google_maps_key",
                    "AIzaSyD5jqwKMm1SeoYsW25vxCXfTlhDBeZ4H5c")
        }
        getByName("debug") {
            manifestPlaceholders = mapOf(Pair("crashlyticsEnabled", false))
            resValue("string",
                    "google_maps_key",
                    "AIzaSyAhJx57ikQH9rYc8IT8W3d2As5cGHMBvuo")
        }

    }
    dataBinding {
        isEnabled = true
    }

    signingConfigs {
        // We need to sign debug builds with a debug key to make firebase auth happy
        getByName("debug") {
            storeFile = file("../debug.keystore")
            keyAlias = "androiddebugkey"
            keyPassword = "android"
            storePassword = "android"
        }
    }

    // debug and release variants share the same source dir
    //sourceSets {
    //    debug.java.srcDir= listOf("src/debugRelease/java")
    //    release.java.srcDir= listOf("src/debugRelease/java")
    //}

    lintOptions {
        isAbortOnError = false
        //disable "InvalidPackage", "MissingTranslation"
    }

    testBuildType = "debug"
}

dependencies {
    implementation(project(":shared"))
    testImplementation(project(":test-shared"))
    testImplementation(project(":androidTest-shared"))

    implementation(fileTree("dir" to "libs", "include" to "*.jar"))

    // UI
    implementation("androidx.appcompat:appcompat:${Versions.appcompatVersion}")
    implementation("androidx.browser:browser:${Versions.browserVersion}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}")
    implementation("com.google.android.material:material:${Versions.materialVersion}")
    implementation("com.google.android:flexbox:${Versions.flexboxVersion}")
    implementation("com.airbnb.android:lottie:${Versions.lottieVersion}")
    implementation("com.pacioianu.david:ink-page-indicator:${Versions.pageIndicatorVersion}")

    // Architecture Components
    implementation("androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}")
    kapt("androidx.lifecycle:lifecycle-compiler:${Versions.lifecycleVersion}")
    testImplementation("androidx.arch.core:core-testing:${Versions.lifecycleVersion}")

    // Dagger
    implementation("com.google.dagger:dagger-android:${Versions.dagger}")
    implementation("com.google.dagger:dagger-android-support:${Versions.dagger}")
    kapt("com.google.dagger:dagger-compiler:${Versions.dagger}")
    kapt("com.google.dagger:dagger-android-processor:${Versions.dagger}")

    // Glide
    implementation("com.github.bumptech.glide:glide:${Versions.glideVersion}")
    annotationProcessor("com.github.bumptech.glide:compiler:${Versions.glideVersion}")

    // Fabric and Firebase
    implementation("com.firebaseui:firebase-ui-auth:${Versions.firebaseUiVersion}")
    implementation("com.crashlytics.sdk.android:crashlytics:${Versions.crashlyticsVersion}")

    // Utils
    // Date and time API for Java.
    api("com.jakewharton.threetenabp:threetenabp:${Versions.threetenabpVersion}")
    testImplementation("org.threeten:threetenbp:${Versions.threetenbpVersion}")
    implementation("androidx.core:core-ktx:${Versions.ktxVersion}")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}")

    // Instrumentation tests
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.espressoVersion}")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:${Versions.espressoVersion}")
    androidTestImplementation("androidx.test:runner:${Versions.runnerVersion}")
    androidTestImplementation("androidx.test:rules:${Versions.rulesVersion}")

    // Local unit tests
    testImplementation("junit:junit:${Versions.junitVersion}")
    testImplementation("org.mockito:mockito-core:${Versions.mockitoVersion}")
    testImplementation("com.nhaarman:mockito-kotlin:${Versions.mockitoKotlinVersion}")
    testImplementation("org.hamcrest:hamcrest-library:${Versions.hamcrestVersion}")

    // Leak Canary
    debugImplementation("com.squareup.leakcanary:leakcanary-android:${Versions.leakCanaryVersion}")
    //stagingImplementation("com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakCanaryVersion}")
    releaseImplementation("com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakCanaryVersion}")

    // Solve conflicts with gson. DataBinding is using an old version.
    implementation("com.google.code.gson:gson:${Versions.gsonVersion}")

}