package com.alexmprog.thecocktails.feature.cocktail.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.alexmprog.thecocktails.core.domain.usecase.GetCocktailDetailsUseCase
import com.alexmprog.thecocktails.core.domain.model.Cocktail
import com.alexmprog.thecocktails.core.domain.model.CocktailDetails
import com.alexmprog.thecocktails.core.common.model.Resource
import com.alexmprog.thecocktails.core.ui.state.ErrorText
import com.alexmprog.thecocktails.core.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
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

    private val refreshAction = Channel<Boolean>(CONFLATED)
    val detailsState: StateFlow<UiState<CocktailDetails>> = refreshAction.receiveAsFlow()
        .flatMapLatest { getCocktailDetailsUseCase(route.id) }
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

    init {
        refresh()
    }

    fun refresh() {
        refreshAction.trySend(true)
    }
}