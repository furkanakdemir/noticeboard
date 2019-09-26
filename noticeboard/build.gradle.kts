import com.android.build.gradle.internal.dsl.TestOptions
import com.jfrog.bintray.gradle.BintrayExtension
import java.io.FileInputStream
import java.util.*

plugins {
    id(Plugins.android_library)
    kotlin(Plugins.kotlin_android)
    kotlin(Plugins.kotlin_android_extensions)
    kotlin(Plugins.kapt)
    id(Plugins.ktlint)
    id(Plugins.dex_count)
    id(Plugins.detekt)
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.4"
}

android {
    compileSdkVersion(AndroidSdk.sdk_compile)

    defaultConfig {
        minSdkVersion(AndroidSdk.sdk_minimum)
        targetSdkVersion(AndroidSdk.sdk_target)
        versionCode = AndroidSdk.version_code
        versionName = AndroidSdk.version_name
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        consumerProguardFiles("consumer-proguard-rules.pro")
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    testOptions {
        unitTests(delegateClosureOf<TestOptions.UnitTestOptions> {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        })
    }
}

val developerId = "furkanakdemir"
val developerName = "Furkan Akdemir"
val artifactId = "noticeboard"
val artifactGroup = "net.furkanakdemir"
val artifactDescription = "Change Log library for Android API 21+"

val publicationName = "noticeboard"

val artifactVersion = "1.0.19"
val vcs = "https://github.com/furkanakdemir/noticeboard"

val androidSourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(android.sourceSets.getByName("main").java.srcDirs)
}

publishing {
    publications {
        create<MavenPublication>("noticeboard") {
            groupId = artifactGroup
            artifactId = artifactId
            version = artifactVersion
            artifact("$buildDir/outputs/aar/noticeboard-release.aar")
            artifact(androidSourcesJar.get())
            pom {
                name.set("NoticeBoard")
                description.set(artifactDescription)
                url.set(vcs)
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set(developerId)
                        name.set(developerName)
                    }
                }

                scm {
                    url.set(vcs)
                }
            }
        }
    }
}

val properties = Properties()
val fis = FileInputStream(rootProject.file("local.properties"))
properties.load(fis)

val bintrayUser: String by lazy { properties.getProperty("bintrayUser") }
val bintrayKey: String by lazy { properties.getProperty("bintrayKey") }

bintray {

    user = bintrayUser
    key = bintrayKey
    publish = true
    setPublications(publicationName)
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "noticeboard"
        name = "net.furkanakdemir.noticeboard"
        websiteUrl = vcs
        githubRepo = "furkanakdemir/noticeboard"
        vcsUrl = vcs
        description = artifactDescription
        setLabels("kotlin")
        setLicenses("Apache-2.0")
        desc = description

        // Configure version
        version.apply {
            name = artifactVersion
            released = Date().toString()
        }
    })
}

dependencies {
    implementation(Deps.kotlin)
    implementation(Deps.appcompat)
    implementation(Deps.core_ktx)
    implementation(Deps.constraintlayout)
    implementation(Deps.recyclerview)
    implementation(Deps.material)

    implementation(Deps.moshi)

    implementation(Deps.lifecycle_extensions)

    // Test Dependencies

    // JUnit
    testImplementation(TestDeps.junit)
    testImplementation(TestDeps.junit)

    // AndroidJUnitRunner and JUnit Rules
    testImplementation(TestDeps.test_runner)
    testImplementation(TestDeps.test_rules)

    // Core library
    testImplementation(TestDeps.mockito_core)
    testImplementation(TestDeps.mockito_kotlin)
    testImplementation(TestDeps.mockito_inline)

    testImplementation(TestDeps.robolectric)
}