pluginManagement {
    includeBuild("build-logic") // WARN: Don't remove! This one's important.
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        mavenLocal()
    }
}

rootProject.name = "core-stubs"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":core-util")
include(":model-film")
include(":model-provider")
include(":provider")