plugins {
    alias(libs.plugins.flixclusive.library)
}

android {
    namespace = "com.flixclusive.model.film"
}

dependencies {
    api(libs.gson)
}