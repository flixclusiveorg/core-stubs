
import com.android.build.gradle.LibraryExtension
import org.jetbrains.dokka.DokkaConfiguration
import org.jetbrains.dokka.gradle.DokkaTaskPartial
import java.net.URL

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
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.dokka)
}

tasks.dokkaHtmlMultiModule {
    moduleName.set("Provider API Reference")
}

fun Project.publishing(configuration: PublishingExtension.() -> Unit)
    = extensions.getByName<PublishingExtension>("publishing").configuration()

fun Project.android(configuration: LibraryExtension.() -> Unit)
    = extensions.getByName<LibraryExtension>("android").configuration()

subprojects {
    group = "com.github.flixclusive"
    version = "1.1.0"

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
                            from(components["java"])
                        }
                    }
                }
            }
        }
    }

    tasks.withType<DokkaTaskPartial>().configureEach {
        dokkaSourceSets.configureEach {
            documentedVisibilities.set(
                setOf(
                    DokkaConfiguration.Visibility.PUBLIC,
                    DokkaConfiguration.Visibility.PROTECTED
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
                remoteUrl.set(URL("$repository/$branch/$projectName/$sourceSetDir"))
                remoteLineSuffix.set("#L")
            }
        }
    }
}