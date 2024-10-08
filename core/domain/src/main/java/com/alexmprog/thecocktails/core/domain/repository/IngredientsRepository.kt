package com.alexmprog.thecocktails.core.domain.repository

import com.alexmprog.thecocktails.core.common.model.Resource
import com.alexmprog.thecocktails.core.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

interface IngredientsRepository {

    fun getIngredients(): Flow<Resource<List<Ingredient>>>
}
