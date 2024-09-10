package com.alexmprog.thecocktails.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.alexmprog.thecocktails.core.model.CocktailsSearchSource
import com.alexmprog.thecocktails.feature.cocktail.details.cocktailDetailsScreenRoute
import com.alexmprog.thecocktails.feature.cocktail.details.navigateToCocktailDetails
import com.alexmprog.thecocktails.ui.home.HomeScreenRoute
import com.alexmprog.thecocktails.ui.home.homeScreenRoute
import com.alexmprog.thecocktails.feature.settings.navigateToSettings
import com.alexmprog.thecocktails.feature.settings.settingsScreenRoute
import com.alexmprog.thecocktails.festure.cocktails.list.cocktailsScreenRoute
import com.alexmprog.thecocktails.festure.cocktails.list.navigateToCocktails

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CocktailsApp(appState: CocktailsAppState) {
    val navController = appState.navController
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = HomeScreenRoute,
            modifier = Modifier.fillMaxSize(),
        ) {
            homeScreenRoute(
                useBottomBar = appState.userSettings.useBottomNavBar,
                onCategoryClick = {
                    navController.navigateToCocktails(it.name, CocktailsSearchSource.Category)
                }, onIngredientClick = {
                    navController.navigateToCocktails(it.name, CocktailsSearchSource.Ingredient)
                }, onGlassClick = {
                    navController.navigateToCocktails(it.name, CocktailsSearchSource.Glass)
                }, onSettingsClick = {
                    navController.navigateToSettings()
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

            settingsScreenRoute { navController.navigateUp() }
        }
    }
}