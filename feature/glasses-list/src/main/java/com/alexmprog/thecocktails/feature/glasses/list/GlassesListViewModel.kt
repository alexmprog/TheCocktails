package com.alexmprog.thecocktails.feature.glasses.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexmprog.thecocktails.core.domain.GetGlassesUseCase
import com.alexmprog.thecocktails.core.model.Glass
import com.alexmprog.thecocktails.core.ui.state.ViewState
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

    val uiState: StateFlow<ViewState<List<Glass>>> = getGlassesUseCase()
        .map { ViewState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ViewState.Loading,
        )
}