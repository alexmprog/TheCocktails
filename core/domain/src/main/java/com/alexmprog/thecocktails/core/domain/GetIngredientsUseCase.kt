package com.alexmprog.thecocktails.core.domain

import com.alexmprog.thecocktails.core.data.repository.IngredientsRepository
import com.alexmprog.thecocktails.core.model.Ingredient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIngredientsUseCase @Inject constructor(
    private val ingredientsRepository: IngredientsRepository
) {
    operator fun invoke(): Flow<List<Ingredient>> = ingredientsRepository.getIngredients()
}