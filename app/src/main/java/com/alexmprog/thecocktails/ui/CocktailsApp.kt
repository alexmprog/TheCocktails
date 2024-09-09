package com.alexmprog.thecocktails.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.alexmprog.thecocktails.core.model.CocktailsSearchSource
import com.alexmprog.thecocktails.core.ui.theme.CocktailsTheme
import com.alexmprog.thecocktails.feature.cocktail.details.cocktailDetailsScreenRoute
import com.alexmprog.thecocktails.feature.cocktail.details.navigateToCocktailDetails
import com.alexmprog.thecocktails.feature.home.HomeScreenRoute
import com.alexmprog.thecocktails.feature.home.homeScreenRoute
import com.alexmprog.thecocktails.festure.cocktails.list.cocktailsScreenRoute
import com.alexmprog.thecocktails.festure.cocktails.list.navigateToCocktails

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CocktailsApp(appState: CocktailsAppState) {
    CocktailsTheme {
        val navController = appState.navController
        SharedTransitionLayout {
            NavHost(
                navController = navController,
                startDestination = HomeScreenRoute,
                modifier = Modifier.fillMaxSize(),
            ) {
                homeScreenRoute(
                    useBottomBar = true,
                    onCategoryClick = {
                        navController.navigateToCocktails(it.name, CocktailsSearchSource.Category)
                    }, onIngredientClick = {
                        navController.navigateToCocktails(it.name, CocktailsSearchSource.Ingredient)
                    }, onGlassClick = {
                        navController.navigateToCocktails(it.name, CocktailsSearchSource.Glass)
                    }
                )

                cocktailsScreenRoute(
                    this@SharedTransitionLayout,
                    onCocktailClick = {
                        navController.navigateToCocktailDetails(it)
                    }, navigateUp = { navController.navigateUp() }
                )

                cocktailDetailsScreenRoute(
                    this@SharedTransitionLayout,
                    navigateUp = { navController.navigateUp() }
                )
            }
        }

    }
}