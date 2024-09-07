plugins {
    alias(libs.plugins.convention.android.library.compose)
}

android {
    namespace = "com.alexmprog.thecocktails.core.ui"
}

dependencies {
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.compose.ui.test)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.testManifest)
}