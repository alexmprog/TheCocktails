plugins {
    alias(libs.plugins.convention.android.library.compose)
    alias(libs.plugins.convention.hilt)
    alias(libs.plugins.convention.serialization)
}

android {
    namespace = "com.alexmprog.thecocktails.feature.cocktails.list"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.domain)
    implementation(projects.core.ui)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.compose.navigation)
    implementation(libs.compose.hilt.navigation)
    implementation(libs.androidx.compose.material3)
    implementation(libs.coil.kt.compose)
    testImplementation(libs.junit)
}

composeCompiler {
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
    metricsDestination = layout.buildDirectory.dir("compose_compiler")
}