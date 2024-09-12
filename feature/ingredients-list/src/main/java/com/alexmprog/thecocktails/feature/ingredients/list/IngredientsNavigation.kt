package com.alexmprog.thecocktails.feature.ingredients.list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexmprog.thecocktails.core.model.Ingredient
import com.alexmprog.thecocktails.core.ui.navigation.ScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object IngredientsListScreenRoute : ScreenRoute

fun NavGraphBuilder.ingredientsScreenRoute(onIngredientClick: (Ingredient) -> Unit) {
    composable<IngredientsListScreenRoute> {
        IngredientsListScreen(onIngredientClick = onIngredientClick)
    }
}