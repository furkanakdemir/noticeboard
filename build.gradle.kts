import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id(Plugins.detekt).version(Versions.detekt_plugin)
}

buildscript {
    repositories {
        google()
        jcenter()

        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath(Plugins.android_gradle_plugin)
        classpath(Plugins.kotlin_gradle_plugin)
        classpath(Plugins.ktlint_plugin)
        classpath(Plugins.dex_count_plugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

subprojects {

    apply {
        plugin(Plugins.detekt)
        plugin(Plugins.ktlint)
    }

    tasks.withType<Detekt>().configureEach {
        exclude("resources/")
        exclude(".*build.*")
        exclude(".*/tmp/.*")
    }

    detekt {
        config = files("$rootDir/qa/detekt.yml")
        parallel = true

        reports {
            xml {
                enabled = true
                destination = file("build/reports/detekt.xml")
            }

            html {
                enabled = true
                destination = file("build/reports/detekt.html")
            }
        }
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}
