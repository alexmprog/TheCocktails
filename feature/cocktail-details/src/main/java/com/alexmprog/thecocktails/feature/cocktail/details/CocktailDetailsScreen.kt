package com.alexmprog.thecocktails.feature.cocktail.details

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alexmprog.thecocktails.core.domain.model.Cocktail
import com.alexmprog.thecocktails.core.domain.model.CocktailDetails
import com.alexmprog.thecocktails.core.ui.components.ErrorView
import com.alexmprog.thecocktails.core.ui.components.LoadingView
import com.alexmprog.thecocktails.core.ui.state.UiState

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun CocktailDetailsScreen(
    cocktail: Cocktail,
    detailsState: UiState<CocktailDetails>,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    onRefreshClick: () -> Unit
) {
    with(sharedTransitionScope) {
        Scaffold(modifier,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            cocktail.name,
                            modifier = Modifier
                                .sharedElement(
                                    state = rememberSharedContentState(key = "cocktail_name_${cocktail.id}"),
                                    animatedVisibilityScope = animatedVisibilityScope
                                )
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
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(cocktail.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = cocktail.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .sharedElement(
                            state = rememberSharedContentState(key = "cocktail_image_${cocktail.id}"),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                        .fillMaxWidth()
                        .height(300.dp)
                )

                when (detailsState) {
                    is UiState.Loading -> LoadingView()
                    is UiState.Error -> ErrorView(detailsState.error, onRefreshClick)
                    is UiState.Success -> CocktailDetails(detailsState.data)
                }
            }
        }
    }
}

@Composable
internal fun ColumnScope.CocktailDetails(details: CocktailDetails) {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(stringResource(R.string.feature_cocktail_details_category))
            }
            append(" ${details.category}")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 2.dp),
        textAlign = TextAlign.Start
    )
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(stringResource(R.string.feature_cocktail_details_glass))
            }
            append(" ${details.glass}")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 2.dp),
        textAlign = TextAlign.Start
    )
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(stringResource(R.string.feature_cocktail_details_instructions))
            }
            append(" ${details.description}")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 2.dp),
        textAlign = TextAlign.Start
    )

    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(stringResource(R.string.feature_cocktail_details_ingredients))
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 2.dp),
        textAlign = TextAlign.Start
    )
    details.measuredIngredients.forEach {
        Text(
            it,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 2.dp),
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Start
        )
    }
}