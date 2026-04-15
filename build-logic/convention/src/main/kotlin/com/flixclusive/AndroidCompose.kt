package com.flixclusive

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("UnstableApiUsage")
internal fun Project.configureAndroidCompose(
    libraryExtension: LibraryExtension,
) {
    libraryExtension.apply {
        buildFeatures.apply {
            compose = true
            viewBinding = true
        }

        dependencies {
            val bom = libs.findLibrary("compose-bom").get()
            add("compileOnly", platform(bom))
            add("androidTestCompileOnly", platform(bom))
        }
    }
}