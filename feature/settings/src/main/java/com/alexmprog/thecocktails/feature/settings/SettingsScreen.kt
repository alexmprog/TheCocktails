package com.alexmprog.thecocktails.feature.settings

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alexmprog.thecocktails.core.model.UserSettings
import com.alexmprog.thecocktails.core.ui.state.ViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsScreen(
    uiState: ViewState<UserSettings>,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    onSaveClick: (UserSettings) -> Unit
) {
    if (uiState is ViewState.Success) {
        var useBottomNavBar by remember { mutableStateOf(uiState.data.useBottomNavBar) }
        var useDynamicColors by remember { mutableStateOf(uiState.data.useDynamicColors) }
        Scaffold(modifier,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Settings",
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
                modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Use bottom navigation bar",
                        modifier = Modifier
                            .padding(8.dp),
                        textAlign = TextAlign.Start
                    )
                    Switch(modifier = Modifier
                        .padding(8.dp),
                        checked = useBottomNavBar,
                        onCheckedChange = { useBottomNavBar = it })
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Use dynamic colors",
                        modifier = Modifier
                            .padding(8.dp),
                        textAlign = TextAlign.Start
                    )
                    Switch(modifier = Modifier
                        .padding(8.dp),
                        checked = useDynamicColors,
                        onCheckedChange = { useDynamicColors = it })
                }
                Button(modifier = Modifier
                    .padding(8.dp), onClick = {
                    onSaveClick(
                        UserSettings(
                            useDynamicColors = useDynamicColors,
                            useBottomNavBar = useBottomNavBar
                        )
                    )
                }) {
                    Text(
                        "Save",
                        modifier = Modifier
                            .padding(8.dp),
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}

