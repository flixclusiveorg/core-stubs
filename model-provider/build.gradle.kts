plugins {
    alias(libs.plugins.flixclusive.library)
}

android {
    namespace = "com.flixclusive.model.provider"
}

dependencies {
    api(libs.gson)
}