package com.alexmprog.thecocktails.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexmprog.thecocktails.core.domain.GetUserSettingsUseCase
import com.alexmprog.thecocktails.core.domain.SaveUserSettingsUseCase
import com.alexmprog.thecocktails.core.model.UserSettings
import com.alexmprog.thecocktails.core.ui.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
internal class SettingsViewModel @Inject constructor(
    getUserSettingsUseCase: GetUserSettingsUseCase,
    private val saveUserSettingsUseCase: SaveUserSettingsUseCase
) : ViewModel() {

    val uiState: StateFlow<ViewState<UserSettings>> = getUserSettingsUseCase()
        .map { ViewState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ViewState.Loading,
        )

    fun save(settings: UserSettings) {
        viewModelScope.launch {
            saveUserSettingsUseCase.invoke(settings)
        }
    }
}