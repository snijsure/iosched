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
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    defaultConfig {
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        /*
        buildConfigField("String", "CONFERENCE_TIMEZONE", "${conference_timezone}")
        buildConfigField("String", "CONFERENCE_DAY1_START", "${conference_day1_start}")
        buildConfigField("String", "CONFERENCE_DAY1_END", "${conference_day1_end}")
        buildConfigField("String", "CONFERENCE_DAY2_START", "${conference_day2_start}")
        buildConfigField("String", "CONFERENCE_DAY2_END", "${conference_day2_end}")
        buildConfigField("String", "CONFERENCE_DAY3_START", "${conference_day3_start}")
        buildConfigField("String", "CONFERENCE_DAY3_END", "${conference_day3_end}\"")

        buildConfigField("String",
                "BOOTSTRAP_CONF_DATA_FILENAME",
                "${bootstrap_conference_data_filename}")

        buildConfigField("String",
                "CONFERENCE_WIFI_OFFERING_START",
                "${conference_wifi_offering_start}")
        */

        consumerProguardFiles("consumer-proguard-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            buildConfigField("String", "REGISTRATION_ENDPOINT_URL", "\"https://events-d07ac.appspot.com/_ah/api/registration/v1/register\"")
            buildConfigField("String", "CONFERENCE_DATA_URL", "\"https://firebasestorage.googleapis.com/v0/b/io2018-festivus-prod/o/sessions.json?alt=media&token=89140adf-e228-45a5-9ae3-8ed01547166a\"")
        }
        getByName("debug") {
            buildConfigField("String", "REGISTRATION_ENDPOINT_URL", "\"https://events-dev-62d2e.appspot.com/_ah/api/registration/v1/register\"")
            buildConfigField("String", "CONFERENCE_DATA_URL", "\"https://firebasestorage.googleapis.com/v0/b/io2018-festivus/o/sessions.json?alt=media&token=019af2ec-9fd1-408e-9b86-891e4f66e674\"")
        }

    }

    //lintOptions {
    //    disable "InvalidPackage"
    //}

    // debug and release variants share the same source dir
    //sourceSets {
    //    debug.java.srcDir "src/debugRelease/java"
    //    release.java.srcDir "src/debugRelease/java"
    //}
}

dependencies {
    api(project(":model"))
    implementation(fileTree("dir" to "libs", "include" to "*.jar"))
    testImplementation(project(":test-shared"))
    testImplementation(project(":androidTest-shared"))

    // Architecture Components
    implementation("androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}")
    implementation("androidx.lifecycle:lifecycle-livedata:${Versions.lifecycleVersion}")
    implementation("androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycleVersion}")

    // Maps
    api("com.google.maps.android:android-maps-utils:${Versions.googleMapUtilsVersion}") {
        exclude(group = "com.google.android.gms")
    }
    api("com.google.android.gms:play-services-maps:${Versions.googlePlayServicesMapsVersion}")

    // Utils
    api("com.jakewharton.timber:timber:${Versions.timberVersion}")
    implementation("com.google.code.gson:gson:${Versions.gsonVersion}")
    implementation("androidx.core:core-ktx:${Versions.ktxVersion}")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}")

    // Dagger
    implementation("com.google.dagger:dagger-android:${Versions.dagger}")
    implementation("com.google.dagger:dagger-android-support:${Versions.dagger}")
    kapt("com.google.dagger:dagger-compiler:${Versions.dagger}")
    kapt("com.google.dagger:dagger-android-processor:${Versions.dagger}")

    // Firebase
    api("com.google.firebase:firebase-auth:${Versions.firebaseAuthVersion}")
    api("com.google.firebase:firebase-config:${Versions.firebaseConfigVersion}")
    api("com.google.firebase:firebase-core:${Versions.firebaseCoreVersion}")
    api("com.google.firebase:firebase-firestore:${Versions.firebaseFirestoreVersion}")
    api("com.google.firebase:firebase-messaging:${Versions.firebaseMessagingVersion}")

    // ThreeTenBP for the shared module only. Date and time API for Java.
    testImplementation("org.threeten:threetenbp:${Versions.threetenbpVersion}")
    compileOnly("org.threeten:threetenbp:${Versions.threetenbpVersion}:no-tzdb")

    // Unit tests
    testImplementation("junit:junit:${Versions.junitVersion}")
    testImplementation("org.hamcrest:hamcrest-library:${Versions.hamcrestVersion}")
    testImplementation("org.mockito:mockito-core:${Versions.mockitoVersion}")
    testImplementation("com.nhaarman:mockito-kotlin:${Versions.mockitoKotlinVersion}")

    // unit tests livedata
    testImplementation("androidx.arch.core:core-testing:${Versions.lifecycleVersion}")

}

