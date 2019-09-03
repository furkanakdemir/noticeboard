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
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}
