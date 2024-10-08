package com.alexmprog.thecocktails.core.domain.usecase

import com.alexmprog.thecocktails.core.domain.model.Ingredient
import com.alexmprog.thecocktails.core.common.model.Resource
import com.alexmprog.thecocktails.core.domain.repository.IngredientsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIngredientsUseCase @Inject constructor(
    private val ingredientsRepository: IngredientsRepository
) {
    operator fun invoke(): Flow<Resource<List<Ingredient>>> = ingredientsRepository.getIngredients()
}