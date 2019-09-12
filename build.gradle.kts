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

detekt {
    config =
        files("/qa/default-detekt-config.yml")
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt> {
    exclude("resources/")
    exclude(".*build.*")
    exclude(".*/tmp/.*")
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}
