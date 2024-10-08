package com.alexmprog.thecocktails.core.domain.repository

import com.alexmprog.thecocktails.core.common.model.Resource
import com.alexmprog.thecocktails.core.domain.model.Cocktail
import com.alexmprog.thecocktails.core.domain.model.CocktailDetails
import com.alexmprog.thecocktails.core.domain.model.CocktailsSearchSource
import kotlinx.coroutines.flow.Flow

interface CocktailsRepository {

    fun getCocktailsBySource(id: String, source: CocktailsSearchSource): Flow<Resource<List<Cocktail>>>

    fun getCocktailDetails(id: Int): Flow<Resource<CocktailDetails>>
}