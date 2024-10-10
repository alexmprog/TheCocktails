package com.alexmprog.thecocktails.core.ui.state

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.res.stringResource

@Stable
sealed class UiState<out T> {
    @Stable
    data object Loading : UiState<Nothing>()
    @Stable
    data class Error<T>(val error: ErrorText) : UiState<T>()
    @Stable
    data class Success<T>(val data: T) : UiState<T>()
}

@Stable
sealed class ErrorText {
    class StringResource(@StringRes val id: Int): ErrorText()

    @Composable
    fun asString() = when(this) {
        is StringResource -> stringResource(id)
    }
}
