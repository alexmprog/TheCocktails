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
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(libs.hilt.android.testing)
}