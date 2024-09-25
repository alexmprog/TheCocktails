package com.alexmprog.thecocktails.core.testing.repository

import com.alexmprog.thecocktails.core.data.repository.CocktailsRepository
import com.alexmprog.thecocktails.core.model.Cocktail
import com.alexmprog.thecocktails.core.model.CocktailDetails
import com.alexmprog.thecocktails.core.model.CocktailsSearchSource
import com.alexmprog.thecocktails.core.model.Resource
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestCocktailsRepository : CocktailsRepository {

    private val cocktailsFlow: MutableSharedFlow<Resource<List<Cocktail>>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val cocktailDetailsFlow: MutableSharedFlow<Resource<CocktailDetails>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getCocktailsBySource(
        id: String, source: CocktailsSearchSource
    ): Flow<Resource<List<Cocktail>>> = cocktailsFlow

    override fun getCocktailDetails(id: Int): Flow<Resource<CocktailDetails>> = cocktailDetailsFlow

    fun setCocktails(resource: Resource<List<Cocktail>>) {
        cocktailsFlow.tryEmit(resource)
    }

    fun setCocktailDetails(resource: Resource<CocktailDetails>) {
        cocktailDetailsFlow.tryEmit(resource)
    }
}
