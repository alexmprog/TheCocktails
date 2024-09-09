package com.alexmprog.thecocktails.feature.glasses

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexmprog.thecocktails.core.model.Glass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexmprog.thecocktails.core.ui.navigation.ScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object GlassesListScreenRoute : ScreenRoute

fun NavGraphBuilder.glassesScreenRoute(onGlassClick: (Glass) -> Unit) {
    composable<GlassesListScreenRoute>(
        enterTransition = { fadeIn() },
        popEnterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popExitTransition = { fadeOut() }
    ) {
        val viewModel = hiltViewModel<GlassesListViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        GlassesListScreen(uiState, onGlassClick= onGlassClick)
    }
}