package com.alexmprog.thecocktails.feature.cocktail.details

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexmprog.thecocktails.core.model.Cocktail
import com.alexmprog.thecocktails.core.ui.navigation.ScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data class CocktailDetailsScreenRoute(val id: Int, val name: String, val image: String?) :
    ScreenRoute

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.cocktailDetailsScreenRoute(
    sharedTransitionScope: SharedTransitionScope,
    navigateUp: () -> Unit
) {
    composable<CocktailDetailsScreenRoute>{
        val viewModel: CocktailDetailsViewModel = hiltViewModel()
        val cocktailState by viewModel.cocktailState.collectAsStateWithLifecycle()
        val detailsState by viewModel.detailsState.collectAsStateWithLifecycle()
        CocktailDetailsScreen(
            cocktailState,
            detailsState,
            sharedTransitionScope,
            this@composable,
            navigateUp = navigateUp
        )
    }
}

fun NavController.navigateToCocktailDetails(cocktail: Cocktail) {
    navigate(CocktailDetailsScreenRoute(cocktail.id, cocktail.name, cocktail.image))
}