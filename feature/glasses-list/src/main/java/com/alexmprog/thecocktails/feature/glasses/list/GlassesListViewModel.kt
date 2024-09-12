package com.alexmprog.thecocktails.feature.glasses.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexmprog.thecocktails.core.domain.GetGlassesUseCase
import com.alexmprog.thecocktails.core.model.Glass
import com.alexmprog.thecocktails.core.model.Resource
import com.alexmprog.thecocktails.core.ui.state.ErrorText
import com.alexmprog.thecocktails.core.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class GlassesListViewModel @Inject constructor(
    getGlassesUseCase: GetGlassesUseCase
) : ViewModel() {

    val uiState: StateFlow<UiState<List<Glass>>> = getGlassesUseCase()
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