
import com.flixclusive.configureKotlinJvm
import com.flixclusive.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("java-library")
                apply("maven-publish")
                apply("org.jetbrains.dokka")
                apply("org.jetbrains.kotlin.jvm")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            configureKotlinJvm()

            dependencies {
                add("implementation", libs.findLibrary("kotlinx-serialization").get())
            }
        }
    }

}