plugins {
    alias(libs.plugins.convention.jvm.library)
    alias(libs.plugins.convention.hilt)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}