plugins {
    alias(libs.plugins.convention.android.library.compose)
    alias(libs.plugins.convention.hilt)
    alias(libs.plugins.convention.serialization)
}

android {
    namespace = "com.alexmprog.thecocktails.feature.glasses.list"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.domain)
    implementation(projects.core.ui)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.compose.navigation)
    implementation(libs.compose.hilt.navigation)
    implementation(libs.androidx.compose.material3)
    testImplementation(projects.core.testing)
    androidTestImplementation(projects.core.testing)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.compose.ui.test)
    androidTestImplementation(libs.androidx.compose.ui.testManifest)
    androidTestImplementation(libs.androidx.lifecycle.runtime.testing)
}