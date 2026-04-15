plugins {
    alias(libs.plugins.flixclusive.android.library)
}

android {
    namespace = "com.flixclusive.core.util"
}

dependencies {
    compileOnly(libs.okhttp)

    compileOnly(libs.core.ktx)
    compileOnly(libs.jsoup)
    compileOnly(libs.junit)
    compileOnly(libs.mockk)
    compileOnly(libs.okhttp.dnsoverhttps)
}