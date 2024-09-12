package com.alexmprog.thecocktails.core.domain

import com.alexmprog.thecocktails.core.data.repository.CocktailsRepository
import com.alexmprog.thecocktails.core.model.CocktailDetails
import com.alexmprog.thecocktails.core.model.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCocktailDetailsUseCase @Inject constructor(
    private val cocktailsRepository: CocktailsRepository
) {
    operator fun invoke(id: Int): Flow<Resource<CocktailDetails>> = cocktailsRepository.getCocktailDetails(id)
}