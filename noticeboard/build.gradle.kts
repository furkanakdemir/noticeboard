plugins {
    id(Plugins.android_library)
    kotlin(Plugins.kotlin_android)
    kotlin(Plugins.kotlin_android_extensions)
    kotlin(Plugins.kapt)
    id(Plugins.ktlint)
    id(Plugins.dex_count)
}


android {
    compileSdkVersion(AndroidSdk.sdk_compile)

    defaultConfig {
        minSdkVersion(AndroidSdk.sdk_minimum)
        targetSdkVersion(AndroidSdk.sdk_target)
        versionCode = AndroidSdk.version_code
        versionName = AndroidSdk.version_name
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        consumerProguardFiles("proguard-rules.pro")

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

}

dependencies {
    implementation(Deps.kotlin)
    implementation(Deps.appcompat)
    implementation(Deps.core_ktx)
    implementation(Deps.constraintlayout)
    implementation(Deps.recyclerview)
    implementation(Deps.material)

    implementation(Deps.dagger_android)
    implementation(Deps.dagger_android_support)
    kapt(Deps.dagger_android_processor)
    kapt(Deps.dagger_compiler)

    implementation(Deps.moshi)

    implementation(Deps.lifecycle_extensions)

    // Test Dependencies

    // JUnit
    testImplementation(TestDeps.junit)

    // Core library
    testImplementation(TestDeps.mockito_core)
    testImplementation(TestDeps.mockito_kotlin)
}