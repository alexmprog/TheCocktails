package com.alexmprog.thecocktails.feature.glasses.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexmprog.thecocktails.core.model.Glass
import com.alexmprog.thecocktails.core.ui.components.ErrorView
import com.alexmprog.thecocktails.core.ui.components.LoadingView
import com.alexmprog.thecocktails.core.ui.components.OutlinedTextItem
import com.alexmprog.thecocktails.core.ui.state.UiState

@Composable
internal fun GlassesListScreen(
    viewModel: GlassesListViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onGlassClick: (Glass) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    GlassesListScreen(uiState, modifier, onGlassClick = onGlassClick)
}

@Composable
internal fun GlassesListScreen(
    uiState: UiState<List<Glass>>,
    modifier: Modifier = Modifier,
    onGlassClick: (Glass) -> Unit
) {
    Surface(modifier) {
        when (uiState) {
            is UiState.Loading -> LoadingView()
            is UiState.Error -> ErrorView(uiState.error, {})
            is UiState.Success -> GlassesList(uiState.data, onGlassClick)
        }
    }
}

@Composable
internal fun GlassesList(
    items: List<Glass>, onGlassClick: (Glass) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(items, key = { it.name }) {
            OutlinedTextItem(modifier = Modifier.animateItem(), title = it.name) {
                onGlassClick(it)
            }
        }
    }
}



