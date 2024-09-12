package com.alexmprog.thecocktails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alexmprog.thecocktails.core.domain.GetUserSettingsUseCase
import com.alexmprog.thecocktails.core.model.UserSettings
import com.alexmprog.thecocktails.core.ui.state.UiState
import com.alexmprog.thecocktails.core.ui.theme.CocktailsTheme
import com.alexmprog.thecocktails.ui.CocktailsApp
import com.alexmprog.thecocktails.ui.rememberCocktailsAppState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var getUserSettingsUseCase: GetUserSettingsUseCase

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        // show splash screen
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        // collect UserSettings during splash screen
        var uiState: UiState<UserSettings> by mutableStateOf(UiState.Loading)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState
                    .onEach { uiState = it }
                    .collect()
            }
        }
        splashScreen.setKeepOnScreenCondition {
            when (uiState) {
                is UiState.Loading,
                is UiState.Error -> true

                is UiState.Success -> false
            }
        }
        // enable edge to edge
        enableEdgeToEdge()
        setContent {
            // it's save to skip Loading state because of Splash screen
            if (uiState is UiState.Success) {
                val appState =
                    rememberCocktailsAppState(userSettings = (uiState as UiState.Success).data)
                CocktailsTheme(dynamicColor = appState.userSettings.useDynamicColors) {
                    CocktailsApp(appState)
                }
            }
        }
    }
}