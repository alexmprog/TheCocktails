package com.alexmprog.thecocktails.core.domain

import com.alexmprog.thecocktails.core.data.repository.CocktailsRepository
import com.alexmprog.thecocktails.core.model.Cocktail
import com.alexmprog.thecocktails.core.model.CocktailsSearchSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCocktailsUseCase @Inject constructor(
    private val cocktailsRepository: CocktailsRepository
) {
    operator fun invoke(id: String, source: CocktailsSearchSource): Flow<List<Cocktail>> =
        cocktailsRepository.getCocktailsBySource(id, source)
}