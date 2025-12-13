plugins {
    alias(libs.plugins.com.example.jvm.library)
    alias(libs.plugins.com.example.jvm.hilt)
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
}
