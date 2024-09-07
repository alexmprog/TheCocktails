package com.alexmprog.thecocktails.festure.cocktails

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexmprog.thecocktails.core.model.Cocktail
import com.alexmprog.thecocktails.core.model.CocktailsSearchSource
import com.alexmprog.thecocktails.core.ui.navigation.ScreenRoute
import com.alexmprog.thecocktails.festure.cocktails.details.CocktailDetailsRoute
import com.alexmprog.thecocktails.festure.cocktails.list.CocktailsListRoute
import kotlinx.serialization.Serializable

@Serializable
data class CocktailsListScreenRoute(val id: String, val source: CocktailsSearchSource) : ScreenRoute

@Serializable
data class CocktailDetailsScreenRoute(val id: Int, val name: String, val image: String?) :
    ScreenRoute

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.cocktailsScreenRoute(
    sharedTransitionScope: SharedTransitionScope,
    onCocktailClick: (Cocktail) -> Unit,
    navigateUp: () -> Unit
) {
    composable<CocktailsListScreenRoute>(
        enterTransition = { fadeIn() },
        popEnterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popExitTransition = { fadeOut() }
    ) {
        CocktailsListRoute(
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = this@composable,
            onCocktailClick = onCocktailClick,
            navigateUp = navigateUp
        )
    }
    composable<CocktailDetailsScreenRoute>(
        enterTransition = { fadeIn() },
        popEnterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popExitTransition = { fadeOut() }
    ) {
        CocktailDetailsRoute(
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = this@composable,
            navigateUp = navigateUp
        )
    }
}