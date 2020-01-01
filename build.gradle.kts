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

// Top-level build file where you can add configuration options common to all
// sub-projects/modules.

buildscript {

    repositories {
        google()
        mavenCentral()
        jcenter()
        maven(url = "https://maven.fabric.io/public")
        // Android Build Server
        maven(url = "../iosched-prebuilts/m2repository")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.androidGradlePluginVersion}")
        //classpath(kotlin("gradle-plugin", $kotlinLanguage))
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}")
        classpath("com.google.gms:google-services:${Versions.googleServicesVersion}")
        classpath("io.fabric.tools:gradle:${Versions.fabricVersion}")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()

        // For Android Build Server
        // - Material Design Components
        maven(url = "${rootProject.projectDir}/../iosched-prebuilts/repository")
        // - Other dependencies
        maven(url = "${rootProject.projectDir}/../iosched-prebuilts/m2repository")
        // - Support Libraries, etc
        maven(
                url = "${rootProject.projectDir}/../../../prebuilts/fullsdk/linux/extras/support/m2repository")
    }
}

plugins {
    id("com.github.ben-manes.versions") version ("0.20.0")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
