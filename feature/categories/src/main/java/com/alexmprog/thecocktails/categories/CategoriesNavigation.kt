package com.alexmprog.thecocktails.categories

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexmprog.thecocktails.core.model.Category
import com.alexmprog.thecocktails.core.ui.navigation.ScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object CategoriesListScreenRoute: ScreenRoute

fun NavGraphBuilder.categoriesScreenRoute(onCategoryClick: (Category) -> Unit) {
    composable<CategoriesListScreenRoute>(
        enterTransition = { fadeIn() },
        popEnterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popExitTransition = { fadeOut() }
    ) {
        val viewModel = hiltViewModel<CategoriesListViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        CategoriesListScreen(uiState, onCategoryClick = onCategoryClick)
    }
}