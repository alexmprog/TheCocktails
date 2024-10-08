package com.alexmprog.thecocktails.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alexmprog.thecocktails.core.domain.model.UserSettings

@Stable
class CocktailsAppState(
    val navController: NavHostController,
    val userSettings: UserSettings
)

@Composable
fun rememberCocktailsAppState(
    mainNavController: NavHostController = rememberNavController(),
    userSettings: UserSettings
): CocktailsAppState {
    return remember(mainNavController, userSettings) {
        CocktailsAppState(navController = mainNavController, userSettings = userSettings)
    }
}
