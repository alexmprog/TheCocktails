package com.alexmprog.thecocktails.feature.glasses

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import com.alexmprog.thecocktails.core.model.Glass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexmprog.thecocktails.core.ui.navigation.ScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object GlassesListScreenRoute : ScreenRoute

fun NavGraphBuilder.glassesScreenRoute(onGlassClick: (Glass) -> Unit) {
    composable<GlassesListScreenRoute>(
        enterTransition = { fadeIn() },
        popEnterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popExitTransition = { fadeOut() }
    ) {
        GlassesListRoute(onGlassClick = onGlassClick)
    }
}