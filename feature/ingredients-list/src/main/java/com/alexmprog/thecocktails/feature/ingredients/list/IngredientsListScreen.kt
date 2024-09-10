package com.alexmprog.thecocktails.feature.ingredients.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexmprog.thecocktails.core.model.Ingredient
import com.alexmprog.thecocktails.core.ui.components.OutlinedTextItem
import com.alexmprog.thecocktails.core.ui.state.ViewState

@Composable
internal fun IngredientsListScreen(
    uiState: ViewState<List<Ingredient>>,
    modifier: Modifier = Modifier,
    onIngredientClick: (Ingredient) -> Unit
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
                            onIngredientClick(it)
                        }
                    }
                }
            } else {
                CircularProgressIndicator(Modifier.size(40.dp))
            }
        }
    }
}

