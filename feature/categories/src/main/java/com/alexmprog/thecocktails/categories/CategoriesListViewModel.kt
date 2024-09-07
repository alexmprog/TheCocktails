package com.alexmprog.thecocktails.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexmprog.thecocktails.core.domain.GetCategoriesUseCase
import com.alexmprog.thecocktails.core.model.Category
import com.alexmprog.thecocktails.core.ui.state.ViewState
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

    val uiState: StateFlow<ViewState<List<Category>>> = getCategoriesUseCase()
        .map { ViewState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ViewState.Loading,
        )
}