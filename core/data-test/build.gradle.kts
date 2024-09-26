plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.hilt)
}

android {
    namespace = "com.alexmprog.thecocktails.core.data.test"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.network)
    implementation(projects.core.database)
    implementation(projects.core.model)
    implementation(projects.core.data)
    implementation(libs.hilt.android.testing)
}