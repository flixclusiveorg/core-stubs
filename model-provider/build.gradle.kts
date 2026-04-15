plugins {
    alias(libs.plugins.flixclusive.kotlin.library)
}

dependencies {
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
}