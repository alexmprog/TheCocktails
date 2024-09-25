package com.alexmprog.thecocktails.core.testing.repository

import com.alexmprog.thecocktails.core.data.repository.IngredientsRepository
import com.alexmprog.thecocktails.core.model.Ingredient
import com.alexmprog.thecocktails.core.model.Resource
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestIngredientsRepository : IngredientsRepository {

    private val ingredientsFlow: MutableSharedFlow<Resource<List<Ingredient>>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getIngredients(): Flow<Resource<List<Ingredient>>> = ingredientsFlow

    fun setIngredients(resource: Resource<List<Ingredient>>) {
        ingredientsFlow.tryEmit(resource)
    }
}