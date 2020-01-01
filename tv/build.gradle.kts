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
        applicationId="com.google.samples.apps.iosched.tv"
        minSdkVersion(Versions.minTvSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode= Versions.versionCodeTv
        versionName=Versions.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        manifestPlaceholders = mapOf(Pair("crashlyticsEnabled", true))
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            manifestPlaceholders = mapOf(Pair("crashlyticsEnabled", true))
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        getByName("debug") {
            manifestPlaceholders = mapOf(Pair("crashlyticsEnabled", false))
        }
    }
    dataBinding {
        isEnabled = true
    }

    //lintOptions {
    //    disable "InvalidPackage", "MissingTranslation"
    //}

    // debug and release variants share the same source dir
    //sourceSets {
    //    debug.java.srcDir "src/debugRelease/java"
    //    release.java.srcDir "src/debugRelease/java"
    //}
}

dependencies {
    implementation(project(":shared"))
    testImplementation(project(":test-shared"))
    testImplementation(project(":androidTest-shared"))

    // UI
    implementation("androidx.appcompat:appcompat:${Versions.appcompatVersion}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}")
    implementation("androidx.leanback:leanback:${Versions.leanbackVersion}")
    implementation("androidx.tvprovider:tvprovider:${Versions.tvproviderVersion}")
    implementation("com.google.android.material:material:${Versions.materialVersion}")
    implementation("com.google.android:flexbox:${Versions.flexboxVersion}")

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

    // Utils
    // Date and time API for Java.
    api("com.jakewharton.threetenabp:threetenabp:${Versions.threetenabpVersion}")
    testImplementation("org.threeten:threetenbp:${Versions.threetenbpVersion}")
    implementation("androidx.core:core-ktx:${Versions.ktxVersion}")

    // Fabric and Firebase
    implementation("com.crashlytics.sdk.android:crashlytics:${Versions.crashlyticsVersion}}")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}")

    // Instrumentation tests
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.espressoVersion}")
    androidTestImplementation("androidx.test:runner:${Versions.runnerVersion}")

    // Local unit tests
    testImplementation("junit:junit:${Versions.junitVersion}")
    testImplementation("org.hamcrest:hamcrest-library:${Versions.hamcrestVersion}")

    // Solve conflicts with gson. DataBinding is using an old version.
    implementation("com.google.code.gson:gson:${Versions.gsonVersion}")

    // Solve conflicts with okhttp. Firestore is using an old version.
    implementation("com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}")

}
