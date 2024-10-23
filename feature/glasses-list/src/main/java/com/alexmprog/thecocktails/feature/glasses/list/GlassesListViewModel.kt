package com.alexmprog.thecocktails.feature.glasses.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexmprog.thecocktails.core.domain.usecase.GetGlassesUseCase
import com.alexmprog.thecocktails.core.domain.model.Glass
import com.alexmprog.thecocktails.core.common.model.Resource
import com.alexmprog.thecocktails.core.ui.state.ErrorText
import com.alexmprog.thecocktails.core.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class GlassesListViewModel @Inject constructor(
    getGlassesUseCase: GetGlassesUseCase
) : ViewModel() {

    private val refreshAction = Channel<Boolean>(CONFLATED)
    val uiState: StateFlow<UiState<List<Glass>>> = refreshAction.receiveAsFlow()
        .flatMapLatest { getGlassesUseCase() }
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