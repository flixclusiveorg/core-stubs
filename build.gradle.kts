
import com.android.build.api.dsl.LibraryExtension
import org.jetbrains.dokka.gradle.DokkaExtension
import org.jetbrains.dokka.gradle.engine.parameters.VisibilityModifier

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        mavenLocal()
    }
}

plugins {
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.dokka)
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
}

dokka {
    dokkaPublications.html {
        moduleName.set("Provider API Reference")
    }
}

fun Project.publishing(configuration: PublishingExtension.() -> Unit)
    = extensions.getByName<PublishingExtension>("publishing").configuration()

fun Project.android(configuration: LibraryExtension.() -> Unit)
    = extensions.getByName<LibraryExtension>("android").configuration()

subprojects {
    apply(plugin = "org.jetbrains.dokka")

    group = "com.github.flixclusive"
    version = "1.3.0"

    afterEvaluate {
        publishing {
            repositories {
                mavenLocal()
            }

            publications {
                register<MavenPublication>("release") {
                    afterEvaluate {
                        if (plugins.hasPlugin("com.android.library")) {
                            from(components["release"])
                        } else {
                            from(components["kotlin"])
                        }
                    }
                }
            }
        }
    }

    extensions.configure<DokkaExtension> {
        dokkaSourceSets.configureEach {
            documentedVisibilities.set(
                setOf(
                    VisibilityModifier.Public,
                    VisibilityModifier.Protected
                )
            )

            includes.from("README.md")

            reportUndocumented.set(true)
            // failOnWarning.set(true)

            sourceLink {
                val projectName = project.name
                val branch = "master"
                val repository = "https://github.com/flixclusiveorg/core-stubs/tree"
                val sourceSetDir = "src/main/kotlin"

                localDirectory.set(projectDir.resolve(sourceSetDir))
                remoteUrl("$repository/$branch/$projectName/$sourceSetDir")
                remoteLineSuffix.set("#L")
            }
        }
    }
}