import dev.iurysouza.modulegraph.LinkText
import dev.iurysouza.modulegraph.ModuleType
import dev.iurysouza.modulegraph.Orientation
import dev.iurysouza.modulegraph.Theme

plugins {
    alias(libs.plugins.convention.android.application.compose)
    alias(libs.plugins.convention.hilt)
    alias(libs.plugins.convention.serialization)
    alias(libs.plugins.graph)
}

android {
    namespace = "com.alexmprog.thecocktails"

    defaultConfig {
        applicationId = "com.alexmprog.thecocktails"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.domain)
    implementation(projects.core.ui)
    implementation(projects.feature.categoriesList)
    implementation(projects.feature.cocktailsList)
    implementation(projects.feature.cocktailDetails)
    implementation(projects.feature.glassesList)
    implementation(projects.feature.ingredientsList)
    implementation(projects.feature.settings)
    implementation(libs.androidx.core.ktx)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    implementation(libs.compose.navigation)
    implementation(libs.compose.hilt.navigation)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.compose.ui.test)
    debugImplementation(libs.androidx.compose.ui.testManifest)
}

moduleGraphConfig {
    readmePath.set("../README.md")
    heading = "## Module Graph"
    showFullPath.set(false)
    orientation.set(Orientation.TOP_TO_BOTTOM)
    linkText.set(LinkText.NONE)
    setStyleByModuleType.set(true)
    theme.set(
        Theme.BASE(
            themeVariables = mapOf(
                // Text
                "primaryTextColor" to "#F6F8FAff",
                // Node
                "primaryColor" to "#5a4f7c",
                // Node border
                "primaryBorderColor" to "#5a4f7c",
                // Container box background
                "tertiaryColor" to "#40375c",
                "lineColor" to "#f5a623",
                "fontSize" to "12px",
            ),
            focusColor = "#F5A622",
            moduleTypes = listOf(ModuleType.Kotlin("#2C4162")),
        ),
    )
}