package com.alexmprog.thecocktails.feature.ingredients

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexmprog.thecocktails.core.model.Ingredient
import com.alexmprog.thecocktails.core.ui.navigation.ScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object IngredientsListScreenRoute : ScreenRoute

fun NavGraphBuilder.ingredientsScreenRoute(onIngredientClick: (Ingredient) -> Unit) {
    composable<IngredientsListScreenRoute>(
        enterTransition = { fadeIn() },
        popEnterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popExitTransition = { fadeOut() }
    ) {
        val viewModel = hiltViewModel<IngredientsListViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        IngredientsListScreen(uiState, onIngredientClick = onIngredientClick)
    }
}