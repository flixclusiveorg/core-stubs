plugins {
    alias(libs.plugins.flixclusive.android.library)
}

android {
    namespace = "com.flixclusive.core.util"
}

dependencies {
    api(libs.okhttp)
    api(libs.okhttp.dnsoverhttps)
    api(libs.gson)
    api(libs.jsoup)
    api(libs.retrofit)

    implementation(libs.core.ktx)
    implementation(libs.junit)
    implementation(libs.mockk)
}