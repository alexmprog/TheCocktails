package com.alexmprog.thecocktails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexmprog.thecocktails.core.domain.usecase.GetUserSettingsUseCase
import com.alexmprog.thecocktails.core.domain.model.UserSettings
import com.alexmprog.thecocktails.core.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    getUserSettingsUseCase: GetUserSettingsUseCase
) : ViewModel() {

    val uiState: StateFlow<UiState<UserSettings>> = getUserSettingsUseCase()
        .map { UiState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading,
        )
}