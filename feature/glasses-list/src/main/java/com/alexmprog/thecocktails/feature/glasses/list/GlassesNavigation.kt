package com.alexmprog.thecocktails.feature.glasses.list

import com.alexmprog.thecocktails.core.model.Glass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexmprog.thecocktails.core.ui.navigation.ScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object GlassesListScreenRoute : ScreenRoute

fun NavGraphBuilder.glassesScreenRoute(onGlassClick: (Glass) -> Unit) {
    composable<GlassesListScreenRoute> {
        GlassesListScreen(onGlassClick = onGlassClick)
    }
}