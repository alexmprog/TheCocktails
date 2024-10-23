package com.alexmprog.thecocktails.festure.cocktails.list

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexmprog.thecocktails.core.domain.model.Cocktail
import com.alexmprog.thecocktails.core.domain.model.CocktailsSearchSource
import com.alexmprog.thecocktails.core.ui.navigation.ScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data class CocktailsListScreenRoute(val id: String, val source: CocktailsSearchSource) : ScreenRoute

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.cocktailsScreenRoute(
    sharedTransitionScope: SharedTransitionScope,
    onCocktailClick: (Cocktail) -> Unit,
    navigateUp: () -> Unit
) {
    composable<CocktailsListScreenRoute>{
        val viewModel = hiltViewModel<CocktailsListViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        CocktailsListScreen(
            uiState,
            sharedTransitionScope,
            this@composable,
            onCocktailClick = onCocktailClick,
            onRefreshClick = viewModel::refresh,
            navigateUp = navigateUp
        )
    }
}

fun NavController.navigateToCocktails(id: String, source: CocktailsSearchSource) {
    navigate(CocktailsListScreenRoute(id, source))
}