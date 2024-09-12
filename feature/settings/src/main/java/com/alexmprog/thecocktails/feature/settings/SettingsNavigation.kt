package com.alexmprog.thecocktails.feature.settings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexmprog.thecocktails.core.ui.navigation.ScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object SettingsScreenRoute : ScreenRoute

fun NavGraphBuilder.settingsScreenRoute(navigateUp: () -> Unit) {
    composable<SettingsScreenRoute>{
        SettingsScreen(navigateUp = navigateUp)
    }
}

fun NavController.navigateToSettings() {
    navigate(SettingsScreenRoute)
}