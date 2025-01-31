package com.alexmprog.thecocktails.categories.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexmprog.thecocktails.core.domain.model.Category
import com.alexmprog.thecocktails.core.ui.components.ErrorView
import com.alexmprog.thecocktails.core.ui.components.LoadingView
import com.alexmprog.thecocktails.core.ui.components.OutlinedTextItem
import com.alexmprog.thecocktails.core.ui.state.UiState

@Composable
internal fun CategoriesListScreen(
    viewModel: CategoriesListViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onCategoryClick: (Category) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CategoriesListScreen(uiState, modifier, onCategoryClick, viewModel::refresh)
}

@Composable
internal fun CategoriesListScreen(
    uiState: UiState<List<Category>>,
    modifier: Modifier = Modifier,
    onCategoryClick: (Category) -> Unit,
    onRefreshClick: () -> Unit
) {
    Surface(modifier) {
        when (uiState) {
            is UiState.Loading -> LoadingView()
            is UiState.Error -> ErrorView(uiState.error, onRefreshClick)
            is UiState.Success -> CategoriesList(uiState.data, onCategoryClick)
        }
    }
}

@Composable
internal fun CategoriesList(
    items: List<Category>, onCategoryClick: (Category) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(items, key = { it.name }) {
            OutlinedTextItem(modifier = Modifier.animateItem(), title = it.name) {
                onCategoryClick(it)
            }
        }
    }
}

@Preview
@Composable
private fun CategoriesListScreenPreview() {
    CategoriesListScreen(
        UiState.Success(listOf(Category("Cocktail"), Category("Drink"), Category("Coffee"))),
        Modifier.fillMaxWidth(), {},{})
}


