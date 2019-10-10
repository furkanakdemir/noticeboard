import com.android.build.gradle.internal.dsl.TestOptions
import com.jfrog.bintray.gradle.BintrayExtension
import java.io.FileInputStream
import java.util.Date
import java.util.Properties

plugins {
    id(Plugins.android_library)
    kotlin(Plugins.kotlin_android)
    kotlin(Plugins.kotlin_android_extensions)
    kotlin(Plugins.kapt)
    id(Plugins.dex_count)
    `maven-publish`
    id(Plugins.bintray) version Versions.bintray_plugin
}

val properties = Properties()
val fis = FileInputStream(rootProject.file("local.properties"))
properties.load(fis)

val noticeBoardStorePassword: String by lazy { properties.getProperty("storePassword") }
val noticeBoardStoreKeyAlias: String by lazy { properties.getProperty("keyAlias") }
val noticeBoardStorePasswordKeyPassword: String by lazy { properties.getProperty("keyPassword") }

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

    signingConfigs {
        create("release") {
            storeFile = rootProject.file("noticeboard.jks")
            storePassword = noticeBoardStorePassword
            keyAlias = noticeBoardStoreKeyAlias
            keyPassword = noticeBoardStorePasswordKeyPassword
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
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

    // TODO replace with https://issuetracker.google.com/issues/72050365 once released.
    libraryVariants.matching { it.buildType.name == "release" }
        .all { generateBuildConfigProvider?.get()?.enabled = false }
}

dependencies {
    implementation(Deps.kotlin)
    implementation(Deps.appcompat)
    implementation(Deps.activity_ktx)
    implementation(Deps.fragment_ktx)
    implementation(Deps.core_ktx)
    implementation(Deps.constraintlayout)
    implementation(Deps.recyclerview)
    implementation(Deps.material)

    implementation(Deps.moshi)

    implementation(Deps.lifecycle_extensions)

    // Test Dependencies

    // JUnit
    testImplementation(TestDeps.junit)

    // AndroidJUnitRunner and JUnit Rules
    testImplementation(TestDeps.test_core)
    testImplementation(TestDeps.test_runner)
    testImplementation(TestDeps.test_rules)

    // Core library
    testImplementation(TestDeps.mockito_core)
    testImplementation(TestDeps.mockito_kotlin)
    testImplementation(TestDeps.mockito_inline)

    testImplementation(TestDeps.robolectric)
}

val publicationName = "noticeboard"

val androidSourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(android.sourceSets.getByName("main").java.srcDirs)
}

val pomArtifactId: String by project
val pomName: String by project
val pomPackaging: String by project
val pomDescription: String by project
val pomUrl: String by project
val pomScmUrl: String by project
val pomScmConnection: String by project
val pomScmDevConnection: String by project
val pomLicenceName: String by project
val pomLicenceUrl: String by project
val pomLicenceDist: String by project
val pomDeveloperId: String by project
val pomDeveloperName: String by project

val group: String by project
val versionName: String by project

val depGroupId = "groupId"
val depArtifactId = "artifactId"
val depVersion = "version"
val depTagParent = "dependencies"
val depTagNode = "dependency"

publishing {
    publications {
        create<MavenPublication>("noticeboard") {
            groupId = group
            artifactId = pomArtifactId
            version = versionName
            artifact("$buildDir/outputs/aar/noticeboard-release.aar")
            artifact(androidSourcesJar.get())
            pom {
                name.set(pomName)
                description.set(pomDescription)
                url.set(pomUrl)
                packaging = pomPackaging
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set(pomDeveloperId)
                        name.set(pomDeveloperName)
                    }
                }

                scm {
                    url.set(pomUrl)
                    connection.set(pomScmConnection)
                    developerConnection.set(pomScmDevConnection)
                }

                // The publication doesn't know about our dependencies, so we have to manually add them to the pom
                withXml {
                    val dependenciesNode = asNode().appendNode(depTagParent)
                    configurations.implementation.get()
                        .allDependencies.withType(ModuleDependency::class.java) {
                        val dependencyNode = dependenciesNode.appendNode(depTagNode)
                        with(this) {
                            dependencyNode.apply {
                                appendNode(depGroupId, group)
                                appendNode(depArtifactId, name)
                                appendNode(depVersion, version)
                            }
                        }
                    }
                }
            }
        }
    }
}

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
        websiteUrl = pomUrl
        githubRepo = "furkanakdemir/noticeboard"
        vcsUrl = pomScmUrl
        description = pomDescription
        setLabels("kotlin")
        setLicenses("Apache-2.0")
        desc = description

        // Configure version
        version.apply {
            name = versionName
            released = Date().toString()
        }
    })
}
