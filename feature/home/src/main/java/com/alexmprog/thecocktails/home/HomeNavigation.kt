package com.alexmprog.thecocktails.home

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Immutable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexmprog.thecocktails.core.domain.model.Category
import com.alexmprog.thecocktails.core.domain.model.Glass
import com.alexmprog.thecocktails.core.domain.model.Ingredient
import com.alexmprog.thecocktails.core.ui.navigation.ScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object HomeScreenRoute : ScreenRoute

@Immutable
class HomeScreens(
    val categoriesScreen: Screen,
    val ingredientsScreen: Screen,
    val glassesScreen: Screen,
){
    @Immutable
    class Screen(
        val route: ScreenRoute,
        val builder: NavGraphBuilder.() -> Unit
    )
}

fun NavGraphBuilder.homeScreenRoute(
    homeScreens: HomeScreens,
    onSettingsClick: () -> Unit
) {
    composable<HomeScreenRoute>(
        enterTransition = { fadeIn() },
        popEnterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popExitTransition = { fadeOut() }
    ) {
        HomeScreen(homeScreens, onSettingsClick)
    }
}