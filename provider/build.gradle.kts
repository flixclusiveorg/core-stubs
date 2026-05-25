plugins {
    alias(libs.plugins.flixclusive.android.library)
    alias(libs.plugins.flixclusive.compose)
}

android {
    namespace = "com.flixclusive.provider"
}

dependencies {
    compileOnly(libs.jsoup)
    compileOnly(libs.okhttp)
    api(projects.coreUtil)
    api(projects.modelProvider)
    api(projects.modelMedia)

    compileOnly(libs.kotlinx.coroutines)
    compileOnly(libs.compose.runtime)
    compileOnly(libs.compose.ui)
    compileOnly(libs.core.ktx)
    compileOnly(libs.datastore)
}