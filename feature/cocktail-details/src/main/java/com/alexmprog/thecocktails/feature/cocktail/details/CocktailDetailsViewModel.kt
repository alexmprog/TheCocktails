package com.alexmprog.thecocktails.feature.cocktail.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.alexmprog.thecocktails.core.domain.GetCocktailDetailsUseCase
import com.alexmprog.thecocktails.core.model.Cocktail
import com.alexmprog.thecocktails.core.model.CocktailDetails
import com.alexmprog.thecocktails.core.model.Resource
import com.alexmprog.thecocktails.core.ui.state.ErrorText
import com.alexmprog.thecocktails.core.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class CocktailDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCocktailDetailsUseCase: GetCocktailDetailsUseCase
) : ViewModel() {

    private val route: CocktailDetailsScreenRoute = savedStateHandle.toRoute()

    val cocktailState: StateFlow<Cocktail> =
        MutableStateFlow(Cocktail(route.id, route.name, route.image))

    val detailsState: StateFlow<UiState<CocktailDetails>> =
        getCocktailDetailsUseCase(route.id)
            .map {
                when (it) {
                    is Resource.Success -> UiState.Success(it.data)
                    is Resource.Error -> UiState.Error(
                        ErrorText.StringResource(com.alexmprog.thecocktails.core.ui.R.string.core_ui_network_error)
                    )
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = UiState.Loading,
            )
}