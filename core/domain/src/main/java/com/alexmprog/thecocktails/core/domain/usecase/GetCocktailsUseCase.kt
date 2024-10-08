package com.alexmprog.thecocktails.core.domain.usecase

import com.alexmprog.thecocktails.core.domain.model.Cocktail
import com.alexmprog.thecocktails.core.domain.model.CocktailsSearchSource
import com.alexmprog.thecocktails.core.common.model.Resource
import com.alexmprog.thecocktails.core.domain.repository.CocktailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCocktailsUseCase @Inject constructor(
    private val cocktailsRepository: CocktailsRepository
) {
    operator fun invoke(id: String, source: CocktailsSearchSource): Flow<Resource<List<Cocktail>>> =
        cocktailsRepository.getCocktailsBySource(id, source)
}