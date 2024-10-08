package com.alexmprog.thecocktails.core.domain.usecase

import com.alexmprog.thecocktails.core.domain.model.CocktailDetails
import com.alexmprog.thecocktails.core.common.model.Resource
import com.alexmprog.thecocktails.core.domain.repository.CocktailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCocktailDetailsUseCase @Inject constructor(
    private val cocktailsRepository: CocktailsRepository
) {
    operator fun invoke(id: Int): Flow<Resource<CocktailDetails>> = cocktailsRepository.getCocktailDetails(id)
}