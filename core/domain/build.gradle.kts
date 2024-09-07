plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.alexmprog.thecocktails.core.domain"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.data)
    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.junit)
}