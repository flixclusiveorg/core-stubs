plugins {
    alias(libs.plugins.flixclusive.library)
    alias(libs.plugins.flixclusive.compose)
}

android {
    namespace = "com.flixclusive.provider"
}

dependencies {
    api(libs.jsoup)
    api(libs.okhttp)
    api(projects.coreUtil)
    api(projects.modelProvider)
    api(projects.modelFilm)

    implementation(libs.compose.runtime)
}