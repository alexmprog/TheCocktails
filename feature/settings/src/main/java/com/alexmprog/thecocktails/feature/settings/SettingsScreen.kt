package com.alexmprog.thecocktails.feature.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexmprog.thecocktails.core.model.UserSettings
import com.alexmprog.thecocktails.core.ui.state.UiState
import com.alexmprog.thecocktails.feature.cocktail.settings.R

@Composable
internal fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SettingsScreen(uiState, modifier, navigateUp) { viewModel.save(it) }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsScreen(
    uiState: UiState<UserSettings>,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    onSaveClick: (UserSettings) -> Unit
) {
    Scaffold(modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.feature_settings_settings),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        textAlign = TextAlign.Start
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
            )
        }) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (uiState is UiState.Success) {
                var useBottomNavBar by remember { mutableStateOf(uiState.data.useBottomNavBar) }
                var useDynamicColors by remember { mutableStateOf(uiState.data.useDynamicColors) }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        stringResource(R.string.feature_settings_use_navigation_bar),
                        textAlign = TextAlign.Start
                    )
                    Switch(checked = useBottomNavBar, onCheckedChange = { useBottomNavBar = it })
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        stringResource(R.string.feature_settings_use_dynamic_colors),
                        textAlign = TextAlign.Start
                    )
                    Switch(checked = useDynamicColors, onCheckedChange = { useDynamicColors = it })
                }
                Button(modifier = Modifier.padding(8.dp), onClick = {
                    onSaveClick(
                        UserSettings(
                            useDynamicColors = useDynamicColors,
                            useBottomNavBar = useBottomNavBar
                        )
                    )
                }) {
                    Text(
                        stringResource(R.string.feature_settings_save),
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}

