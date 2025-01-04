import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.flixclusive.coreStubs.buildLogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    compileOnly(libs.android.gradle)
    compileOnly(libs.kotlin.gradle)
}

gradlePlugin {
    /**
     * Register convention plugins so they are available in the build scripts of the application
     */
    plugins {
        register("flixclusiveKotlinLibrary") {
            id = "flixclusive.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
        register("flixclusiveAndroidLibrary") {
            id = "flixclusive.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("flixclusiveCompose") {
            id = "flixclusive.compose"
            implementationClass = "ComposeConventionPlugin"
        }
    }
}