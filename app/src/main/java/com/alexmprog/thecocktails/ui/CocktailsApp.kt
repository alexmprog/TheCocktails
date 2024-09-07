package com.alexmprog.thecocktails.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexmprog.thecocktails.core.ui.theme.CocktailsTheme
import com.alexmprog.thecocktails.ui.navigation.CocktailsNavHost

@Composable
fun CocktailsApp(appState: CocktailsAppState) {
    CocktailsTheme {
        CocktailsNavHost(appState, modifier = Modifier.fillMaxSize())
    }
}