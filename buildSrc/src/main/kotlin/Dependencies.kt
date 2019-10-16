object Versions {

    const val kotlin_version = "1.3.50"
    const val android_gradle_plugin = "3.5.0"
    const val ktlint_plugin = "8.2.0"
    const val dex_count_plugin = "0.8.6"
    const val detekt_plugin = "1.1.1"
    const val bintray_plugin = "1.8.4"

    const val lifecycle = "2.1.0"
    const val fragment = "1.1.0-rc04"
    const val constraintlayout = "1.1.3"
    const val appcompat = "1.1.0"
    const val material = "1.0.0"
    const val recyclerview = "1.0.0"

    const val junit = "4.12"
    const val espresso = "3.2.0"
    const val robolectric = "4.3"
    const val test_runner = "1.2.0"
    const val test_rules = "1.2.0"
    const val test_core = "1.2.0"
    const val ext_junit = "1.1.1"
    const val ext_truth = "1.2.0"
    const val truth = "1.0"

    const val core_ktx = "1.0.2"
    const val fragment_ktx = "1.1.0"
    const val activity_ktx = "1.0.0"
    const val coroutine = "1.3.0"
    const val mockito_kotlin = "2.1.0"
    const val mockito_core = "2.23.0"

    const val moshi = "1.8.0"
    const val leak_canary = "2.0-beta-3"
}

object Plugins {

    const val android_gradle_plugin =
        "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
    const val kotlin_gradle_plugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
    const val ktlint_plugin = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktlint_plugin}"
    const val dex_count_plugin = "com.getkeepsafe.dexcount:dexcount-gradle-plugin:${Versions.dex_count_plugin}"

    const val android_application = "com.android.application"
    const val android_library = "com.android.library"
    const val kotlin_android = "android"
    const val kotlin_android_extensions = "android.extensions"
    const val kapt = "kapt"
    const val ktlint = "org.jlleitschuh.gradle.ktlint"
    const val dex_count = "com.getkeepsafe.dexcount"
    const val detekt = "io.gitlab.arturbosch.detekt"
    const val bintray = "com.jfrog.bintray"
}

object AndroidSdk {

    const val sdk_minimum = 21
    const val sdk_compile = 29
    const val sdk_target = 29
    const val version_code = 10002
    const val version_name = "1.0.2"
}

object Deps {

    // Kotlin
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin_version}"

    // Android KTX
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_ktx}"
    const val activity_ktx = "androidx.activity:activity-ktx:${Versions.activity_ktx}"

    // AndroidX
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val fragment = "androidx.fragment:fragment:${Versions.fragment}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"

    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"

    // Material
    const val material = "com.google.android.material:material:${Versions.material}"

    // Lifecycle
    const val lifecycle_extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"

    // Json Library
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"

    // Leak Canary
    const val leak_canary = "com.squareup.leakcanary:leakcanary-android:${Versions.leak_canary}"
}

object TestDeps {

    // JUnit
    const val junit = "junit:junit:${Versions.junit}"

    // Core library
    const val test_core = "androidx.test:core:${Versions.test_core}"
    const val mockito_core = "org.mockito:mockito-core:${Versions.mockito_core}"
    const val mockito_inline = "org.mockito:mockito-inline:${Versions.mockito_core}"
    const val mockito_kotlin =
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_kotlin}"

    // AndroidJUnitRunner and JUnit Rules
    const val test_runner = "androidx.test:runner:${Versions.test_runner}"
    const val test_rules = "androidx.test:rules:${Versions.test_rules}"

    // Robolectric
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
}
