package com.alexmprog.thecocktails.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Stable
class CocktailsAppState(
    val navController: NavHostController
)

@Composable
fun rememberCocktailsAppState(
    mainNavController: NavHostController = rememberNavController(),
): CocktailsAppState {
    return remember(mainNavController) {
        CocktailsAppState(navController = mainNavController)
    }
}
