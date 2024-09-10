package com.alexmprog.thecocktails.categories.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexmprog.thecocktails.core.model.Category
import com.alexmprog.thecocktails.core.ui.components.OutlinedTextItem
import com.alexmprog.thecocktails.core.ui.state.ViewState

@Composable
internal fun CategoriesListScreen(
    uiState: ViewState<List<Category>>,
    modifier: Modifier = Modifier,
    onCategoryClick: (Category) -> Unit
) {
    Surface {
        Box(modifier, contentAlignment = Alignment.Center) {
            if (uiState is ViewState.Success) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(uiState.data, key = { it.name }) {
                        OutlinedTextItem(modifier = Modifier.animateItem(), title = it.name){
                            onCategoryClick(it)
                        }
                    }
                }
            } else {
                CircularProgressIndicator(Modifier.size(50.dp))
            }
        }
    }
}

@Preview
@Composable
private fun CategoriesListScreenPreview() {
    CategoriesListScreen(
        ViewState.Success(listOf(Category("test"), Category("name"))),
        Modifier.fillMaxWidth(), {})
}


