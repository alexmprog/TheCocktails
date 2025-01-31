package com.alexmprog.thecocktails.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.alexmprog.thecocktails.categories.list.CategoriesListScreenRoute
import com.alexmprog.thecocktails.categories.list.categoriesScreenRoute
import com.alexmprog.thecocktails.core.domain.model.CocktailsSearchSource
import com.alexmprog.thecocktails.feature.cocktail.details.cocktailDetailsScreenRoute
import com.alexmprog.thecocktails.feature.cocktail.details.navigateToCocktailDetails
import com.alexmprog.thecocktails.feature.glasses.list.glassesScreenRoute
import com.alexmprog.thecocktails.feature.ingredients.list.IngredientsListScreenRoute
import com.alexmprog.thecocktails.feature.ingredients.list.ingredientsScreenRoute
import com.alexmprog.thecocktails.feature.settings.navigateToSettings
import com.alexmprog.thecocktails.feature.settings.settingsScreenRoute
import com.alexmprog.thecocktails.festure.cocktails.list.cocktailsScreenRoute
import com.alexmprog.thecocktails.festure.cocktails.list.navigateToCocktails
import com.alexmprog.thecocktails.home.HomeScreenRoute
import com.alexmprog.thecocktails.home.HomeScreens
import com.alexmprog.thecocktails.home.homeScreenRoute

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CocktailsApp(appState: CocktailsAppState) {
    val navController = appState.navController
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = HomeScreenRoute,
            modifier = Modifier.fillMaxSize(),
            enterTransition = { fadeIn() },
            popEnterTransition = { fadeIn() },
            exitTransition = { fadeOut() },
            popExitTransition = { fadeOut() }
        ) {
            homeScreenRoute(
                homeScreens = HomeScreens(
                    categoriesScreen = HomeScreens.Screen(CategoriesListScreenRoute,{
                        categoriesScreenRoute {
                            navController.navigateToCocktails(it.name, CocktailsSearchSource.Category)
                        }
                    }),
                    ingredientsScreen = HomeScreens.Screen(IngredientsListScreenRoute,{
                        ingredientsScreenRoute {
                            navController.navigateToCocktails(it.name, CocktailsSearchSource.Ingredient)
                        }
                    }),
                    glassesScreen = HomeScreens.Screen(CategoriesListScreenRoute,{
                        glassesScreenRoute {
                            navController.navigateToCocktails(it.name, CocktailsSearchSource.Glass)
                        }
                    })
                ),
                onSettingsClick = {
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