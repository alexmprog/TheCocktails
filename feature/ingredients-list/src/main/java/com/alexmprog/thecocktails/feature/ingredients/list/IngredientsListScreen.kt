package com.alexmprog.thecocktails.feature.ingredients.list

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
import com.alexmprog.thecocktails.core.domain.model.Ingredient
import com.alexmprog.thecocktails.core.ui.components.ErrorView
import com.alexmprog.thecocktails.core.ui.components.LoadingView
import com.alexmprog.thecocktails.core.ui.components.OutlinedTextItem
import com.alexmprog.thecocktails.core.ui.state.UiState

@Composable
internal fun IngredientsListScreen(
    viewModel: IngredientsListViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onIngredientClick: (Ingredient) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    IngredientsListScreen(uiState, modifier, onIngredientClick, viewModel::refresh)
}

@Composable
internal fun IngredientsListScreen(
    uiState: UiState<List<Ingredient>>,
    modifier: Modifier = Modifier,
    onIngredientClick: (Ingredient) -> Unit,
    onRefreshClick: () -> Unit
) {
    Surface(modifier) {
        when (uiState) {
            is UiState.Loading -> LoadingView()
            is UiState.Error -> ErrorView(uiState.error, onRefreshClick)
            is UiState.Success -> IngredientsList(uiState.data, onIngredientClick)
        }
    }
}

@Composable
internal fun IngredientsList(
    items: List<Ingredient>, onIngredientClick: (Ingredient) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(items, key = { it.name }) {
            OutlinedTextItem(modifier = Modifier.animateItem(), title = it.name) {
                onIngredientClick(it)
            }
        }
    }
}
