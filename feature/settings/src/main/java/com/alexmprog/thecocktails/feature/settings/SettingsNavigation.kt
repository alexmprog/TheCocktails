package com.alexmprog.thecocktails.feature.settings

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexmprog.thecocktails.core.ui.navigation.ScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object SettingsScreenRoute : ScreenRoute

fun NavGraphBuilder.settingsScreenRoute(navigateUp: () -> Unit) {
    composable<SettingsScreenRoute>(
        enterTransition = { fadeIn() },
        popEnterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popExitTransition = { fadeOut() }
    ) {
        val viewModel = hiltViewModel<SettingsViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        SettingsScreen(uiState, navigateUp = navigateUp, onSaveClick = { viewModel.save(it) })
    }
}

fun NavController.navigateToSettings() {
    navigate(SettingsScreenRoute)
}