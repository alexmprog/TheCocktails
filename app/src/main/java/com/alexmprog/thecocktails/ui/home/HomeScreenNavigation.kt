package com.alexmprog.thecocktails.ui.home

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alexmprog.thecocktails.categories.CategoriesListScreenRoute
import com.alexmprog.thecocktails.categories.categoriesScreenRoute
import com.alexmprog.thecocktails.core.model.Category
import com.alexmprog.thecocktails.core.model.Glass
import com.alexmprog.thecocktails.core.model.Ingredient
import com.alexmprog.thecocktails.core.ui.navigation.ScreenRoute
import com.alexmprog.thecocktails.feature.glasses.glassesScreenRoute
import com.alexmprog.thecocktails.feature.ingredients.ingredientsScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object HomeScreenRoute : ScreenRoute

fun NavGraphBuilder.homeScreenRoute(
    onCategoryClick: (Category) -> Unit,
    onIngredientClick: (Ingredient) -> Unit,
    onGlassClick: (Glass) -> Unit
) {
    composable<HomeScreenRoute>(
        enterTransition = { fadeIn() },
        popEnterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popExitTransition = { fadeOut() }
    ) {
        HomeScreen { modifier, navController ->
            NavHost(
                modifier = modifier,
                navController = navController,
                startDestination = CategoriesListScreenRoute
            ) {
                categoriesScreenRoute { onCategoryClick(it) }
                ingredientsScreenRoute { onIngredientClick(it) }
                glassesScreenRoute { onGlassClick(it) }
            }
        }
    }
}