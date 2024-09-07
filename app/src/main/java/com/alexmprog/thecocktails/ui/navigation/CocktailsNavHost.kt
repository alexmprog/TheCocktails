package com.alexmprog.thecocktails.ui.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.alexmprog.thecocktails.ui.CocktailsAppState
import com.alexmprog.thecocktails.core.model.CocktailsSearchSource
import com.alexmprog.thecocktails.core.ui.navigation.ScreenRoute
import com.alexmprog.thecocktails.festure.cocktails.CocktailDetailsScreenRoute
import com.alexmprog.thecocktails.festure.cocktails.CocktailsListScreenRoute
import com.alexmprog.thecocktails.festure.cocktails.cocktailsScreenRoute
import com.alexmprog.thecocktails.ui.home.HomeScreenRoute
import com.alexmprog.thecocktails.ui.home.homeScreenRoute

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CocktailsNavHost(
    appState: CocktailsAppState,
    modifier: Modifier,
    screenRoute: ScreenRoute = HomeScreenRoute,
) {
    val navController = appState.navController
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = screenRoute,
            modifier = modifier,
        ) {
            homeScreenRoute(onCategoryClick = {
                navController.navigate(
                    CocktailsListScreenRoute(it.name, CocktailsSearchSource.Category)
                )
            }, onIngredientClick = {
                navController.navigate(
                    CocktailsListScreenRoute(it.name, CocktailsSearchSource.Ingredient)
                )
            }, onGlassClick = {
                navController.navigate(
                    CocktailsListScreenRoute(it.name, CocktailsSearchSource.Glass)
                )
            })

            cocktailsScreenRoute(this@SharedTransitionLayout, onCocktailClick = {
                navController.navigate(
                    CocktailDetailsScreenRoute(it.id, it.name, it.image)
                )
            }, navigateUp = { navController.navigateUp() })
        }
    }
}