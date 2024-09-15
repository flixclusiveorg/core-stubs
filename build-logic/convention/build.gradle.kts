import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.flixclusive.coreStubs.buildLogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
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