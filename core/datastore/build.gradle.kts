plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.hilt)
    alias(libs.plugins.convention.serialization)
}

android {
    namespace = "com.alexmprog.thecocktails.core.datastore"
}

dependencies {
    implementation(projects.core.common)
    implementation(libs.androidx.datastore.preferences)
    testImplementation(libs.junit)
}