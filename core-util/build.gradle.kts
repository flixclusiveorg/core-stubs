plugins {
    alias(libs.plugins.flixclusive.android.library)
}

android {
    namespace = "com.flixclusive.core.util"
}

dependencies {
    api(libs.okhttp)

    implementation(libs.core.ktx)
    implementation(libs.gson)
    implementation(libs.jsoup)
    implementation(libs.junit)
    implementation(libs.mockk)
    implementation(libs.okhttp.dnsoverhttps)
    implementation(libs.retrofit)
}