plugins {
    alias(libs.plugins.flixclusive.kotlin.library)
}

dependencies {
    api(libs.gson)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
}