plugins {
    alias(libs.plugins.com.example.android.library)
    alias(libs.plugins.com.example.android.hilt)
}

android {
    namespace = "com.example.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.network)
    
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
}