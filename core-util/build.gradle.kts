plugins {
    alias(libs.plugins.flixclusive.android.library)
}

android {
    namespace = "com.flixclusive.core.util"
}

dependencies {
    compileOnly(libs.okhttp)

    compileOnly(libs.core.ktx)
    compileOnly(libs.junit)
    compileOnly(libs.okhttp.dnsoverhttps)
    compileOnly(libs.mockk)
    implementation(libs.jsoup)
}