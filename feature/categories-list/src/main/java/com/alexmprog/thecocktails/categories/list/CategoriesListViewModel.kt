package com.alexmprog.thecocktails.categories.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexmprog.thecocktails.core.domain.usecase.GetCategoriesUseCase
import com.alexmprog.thecocktails.core.domain.model.Category
import com.alexmprog.thecocktails.core.ui.state.ErrorText
import com.alexmprog.thecocktails.core.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class CategoriesListViewModel @Inject constructor(
    getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    val uiState: StateFlow<UiState<List<Category>>> = getCategoriesUseCase()
        .map {
            when (it) {
                is com.alexmprog.thecocktails.core.common.model.Resource.Success -> UiState.Success(it.data)
                is com.alexmprog.thecocktails.core.common.model.Resource.Error -> UiState.Error(
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
