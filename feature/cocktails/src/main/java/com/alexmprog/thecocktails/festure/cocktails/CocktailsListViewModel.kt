package com.alexmprog.thecocktails.festure.cocktails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.alexmprog.thecocktails.core.domain.GetCocktailsUseCase
import com.alexmprog.thecocktails.core.model.Cocktail
import com.alexmprog.thecocktails.core.ui.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class CocktailsListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCocktailsUseCase: GetCocktailsUseCase
) : ViewModel() {

    private val route: CocktailsListScreenRoute = savedStateHandle.toRoute()

    val uiState: StateFlow<ViewState<List<Cocktail>>> = getCocktailsUseCase(route.id, route.source)
        .map { ViewState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ViewState.Loading,
        )
}