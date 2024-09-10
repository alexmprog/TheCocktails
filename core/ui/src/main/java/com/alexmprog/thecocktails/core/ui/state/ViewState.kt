package com.alexmprog.thecocktails.core.ui.state

// add error state later
sealed class ViewState<out T> {
    data object Loading : ViewState<Nothing>()
    data class Success<T>(val data: T) : ViewState<T>()
}