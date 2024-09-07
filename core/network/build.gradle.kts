plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.hilt)
    alias(libs.plugins.convention.serialization)
}

android {
    namespace = "com.alexmprog.thecocktails.core.network"

    defaultConfig {
        buildConfigField(
            "String",
            "API_URL",
            "\"https://www.thecocktaildb.com/api/json/v1/1/\""
        )
    }
}

dependencies {
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.coil.kt)
    testImplementation(libs.junit)
}