package com.alexmprog.thecocktails.categories.list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexmprog.thecocktails.core.model.Category
import com.alexmprog.thecocktails.core.ui.navigation.ScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object CategoriesListScreenRoute : ScreenRoute

fun NavGraphBuilder.categoriesScreenRoute(onCategoryClick: (Category) -> Unit) {
    composable<CategoriesListScreenRoute> {
        CategoriesListScreen(onCategoryClick = onCategoryClick)
    }
}