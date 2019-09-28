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
    id(Plugins.bintray) version Versions.bintray_plugin
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

val POM_ARTIFACT_ID: String by project
val POM_NAME: String by project
val POM_PACKAGING: String by project
val GROUP: String by project
val VERSION_NAME: String by project
val POM_DESCRIPTION: String by project
val POM_URL: String by project
val POM_SCM_URL: String by project
val POM_SCM_CONNECTION: String by project
val POM_SCM_DEV_CONNECTION: String by project
val POM_LICENCE_NAME: String by project
val POM_LICENCE_URL: String by project
val POM_LICENCE_DIST: String by project
val POM_DEVELOPER_ID: String by project
val POM_DEVELOPER_NAME: String by project


val DEP_GROUP_ID = "groupId"
val DEP_ARTIFACT_ID = "artifactId"
val DEP_VERSION = "version"
val DEP_TAG_PARENT = "dependencies"
val DEP_TAG_NODE = "dependency"

publishing {
    publications {
        create<MavenPublication>("noticeboard") {
            groupId = GROUP
            artifactId = POM_ARTIFACT_ID
            version = VERSION_NAME
            artifact("$buildDir/outputs/aar/noticeboard-release.aar")
            artifact(androidSourcesJar.get())
            pom {
                name.set(POM_NAME)
                description.set(POM_DESCRIPTION)
                url.set(POM_URL)
                packaging = POM_PACKAGING
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set(POM_DEVELOPER_ID)
                        name.set(POM_DEVELOPER_NAME)
                    }
                }

                scm {
                    url.set(POM_URL)
                    connection.set(POM_SCM_CONNECTION)
                    developerConnection.set(POM_SCM_DEV_CONNECTION)
                }

                //The publication doesn't know about our dependencies, so we have to manually add them to the pom
                withXml {
                    val dependenciesNode = asNode().appendNode(DEP_TAG_PARENT)
                    configurations.implementation.get()
                        .allDependencies.withType(ModuleDependency::class.java) {
                        val dependencyNode = dependenciesNode.appendNode(DEP_TAG_NODE)
                        with(this) {
                            dependencyNode.apply {
                                appendNode(DEP_GROUP_ID, group)
                                appendNode(DEP_ARTIFACT_ID, name)
                                appendNode(DEP_VERSION, version)
                            }
                        }
                    }
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
        websiteUrl = POM_URL
        githubRepo = "furkanakdemir/noticeboard"
        vcsUrl = POM_SCM_URL
        description = POM_DESCRIPTION
        setLabels("kotlin")
        setLicenses("Apache-2.0")
        desc = description

        // Configure version
        version.apply {
            name = VERSION_NAME
            released = Date().toString()
        }
    })
}
