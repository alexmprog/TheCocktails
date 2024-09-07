package com.alexmprog.thecocktails.categories

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexmprog.thecocktails.core.model.Category
import com.alexmprog.thecocktails.core.ui.state.ViewState

@Composable
internal fun CategoriesListRoute(
    viewModel: CategoriesListViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onCategoryClick: (Category) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CategoriesListScreen(uiState, modifier, onCategoryClick)
}

@Composable
internal fun CategoriesListScreen(
    uiState: ViewState<List<Category>>,
    modifier: Modifier,
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
                    items(uiState.data, key = { it.name }) { item: Category ->
                        OutlinedCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize()
                                .animateItem()
                                .clickable { onCategoryClick(item) }
                        ) {
                            Text(
                                text = item.name,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                textAlign = TextAlign.Start,
                            )
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


