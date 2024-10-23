package com.alexmprog.thecocktails.festure.cocktails.list

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alexmprog.thecocktails.core.domain.model.Cocktail
import com.alexmprog.thecocktails.core.ui.components.ErrorView
import com.alexmprog.thecocktails.core.ui.components.LoadingView
import com.alexmprog.thecocktails.core.ui.state.UiState
import com.alexmprog.thecocktails.feature.cocktails.list.R

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun CocktailsListScreen(
    uiState: UiState<List<Cocktail>>,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
    onCocktailClick: (Cocktail) -> Unit,
    onRefreshClick: () -> Unit,
    navigateUp: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(stringResource(R.string.feature_cocktails)) },
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
        Surface(
            modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (uiState) {
                is UiState.Loading -> LoadingView()
                is UiState.Error -> ErrorView(uiState.error, onRefreshClick)
                is UiState.Success -> CocktailsList(
                    uiState.data,
                    sharedTransitionScope,
                    animatedVisibilityScope,
                    onCocktailClick
                )
            }
        }
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun CocktailsList(
    items: List<Cocktail>,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onCocktailClick: (Cocktail) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(items, key = { it.id }) {
            with(sharedTransitionScope) {
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize()
                        .animateItem()
                        .clickable { onCocktailClick(it) }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(it.image)
                                .crossfade(true)
                                .build(),
                            contentDescription = it.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .sharedElement(
                                    state = rememberSharedContentState(key = "cocktail_image_${it.id}"),
                                    animatedVisibilityScope = animatedVisibilityScope
                                )
                                .size(50.dp)
                        )
                        Text(
                            it.name,
                            modifier = Modifier
                                .sharedElement(
                                    state = rememberSharedContentState(key = "cocktail_name_${it.id}"),
                                    animatedVisibilityScope = animatedVisibilityScope
                                )
                                .fillMaxWidth()
                                .padding(8.dp),
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
        }
    }
}
