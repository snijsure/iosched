@file:Suppress("unused")

object Versions {

    const val versionName = "6.1.2" // X.Y.Z; X = Major, Y = minor, Z = Patch level
    const val versionCodeBase = 61020 // XYZZM; M = Module (tv, mobile)
    const val versionCodeTv = versionCodeBase + 2
    const val versionCodeMobile = versionCodeBase + 3

    const val compileSdkVersion = 28
    const val minSdkVersion = 21
    const val minTvSdkVersion = 21 // TV was introduced with Lollipop, min SDK should be 21.
    const val targetSdkVersion = 28

    // App dependencies
    const val androidGradlePluginVersion = "3.5.3"
    const val appcompatVersion = "1.0.0-beta01"
    const val browserVersion = "1.0.0-beta01"
    const val constraintLayoutVersion = "1.1.2"
    const val crashlyticsVersion = "2.9.4"
    const val dagger = "2.16"
    const val dataBindingCompilerVersion = "3.0.1"
    const val espressoVersion = "3.1.0-alpha1"
    const val fabricVersion = "1.25.4"
    const val firebaseAuthVersion = "16.0.2"
    const val firebaseConfigVersion = "16.0.0"
    const val firebaseCoreVersion = "16.0.1"
    const val firebaseFirestoreVersion = "17.0.3"
    const val firebaseMessagingVersion = "17.1.0"
    const val firebaseUiVersion = "3.3.1"
    const val flexboxVersion = "0.3.2"
    const val glideVersion = "4.6.1"
    const val googleMapUtilsVersion = "0.5"
    const val googlePlayServicesMapsVersion = "15.0.1"
    const val googleServicesVersion = "3.2.0"
    const val gsonVersion = "2.8.1"
    const val hamcrestVersion = "1.3"
    const val junitVersion = "4.12"
    const val kotlinVersion = "1.3.61"
    const val ktxVersion = "1.0.0-beta01"
    const val leakCanaryVersion = "1.5.4"
    const val leanbackVersion = "1.0.0-beta01"
    const val legacySupportVersion = "1.0.0-alpha1"
    const val lifecycleVersion = "2.0.0-beta01"
    const val lottieVersion = "2.5.1"
    const val materialVersion = "1.0.0-beta01"
    const val mockitoVersion = "2.8.9"
    const val mockitoKotlinVersion = "1.5.0"
    const val okhttpVersion = "3.10.0"
    const val pageIndicatorVersion = "1.3.0"
    const val rulesVersion = "1.1.0-alpha1"
    const val runnerVersion = "1.1.0-alpha1"
    const val threetenabpVersion = "1.0.5"
    const val threetenbpVersion = "1.3.6"
    const val timberVersion = "4.7.0"
    const val tvproviderVersion = "1.0.0-beta01"
}

object MapDefaults {
    const val map_default_camera_bearing = "1"
    const val map_default_camera_target_lat = "1"
    const val map_default_camera_target_lng = "1"
    const val map_default_camera_tilt = "1"
    const val map_default_camera_zoom = "1"
    const val map_viewport_min_zoom = "1"
}
