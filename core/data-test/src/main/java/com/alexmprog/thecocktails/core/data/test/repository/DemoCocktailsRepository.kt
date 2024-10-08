package com.alexmprog.thecocktails.core.data.test.repository

import com.alexmprog.thecocktails.core.common.dispatchers.CommonDispatchers
import com.alexmprog.thecocktails.core.common.dispatchers.Dispatcher
import com.alexmprog.thecocktails.core.data.repository.toModel
import com.alexmprog.thecocktails.core.domain.model.Cocktail
import com.alexmprog.thecocktails.core.domain.model.CocktailDetails
import com.alexmprog.thecocktails.core.domain.model.CocktailsSearchSource
import com.alexmprog.thecocktails.core.common.model.Resource
import com.alexmprog.thecocktails.core.domain.repository.CocktailsRepository
import com.alexmprog.thecocktails.core.network.demo.DemoNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DemoCocktailsRepository @Inject constructor(
    private val demoNetworkDataSource: DemoNetworkDataSource,
    @Dispatcher(CommonDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : CocktailsRepository {

    override fun getCocktailsBySource(id: String, source: CocktailsSearchSource): Flow<Resource<List<Cocktail>>> = flow {
        emit(
            Resource.Success(
                demoNetworkDataSource.getCocktailsByCategory(id).map { it.toModel() })
        )
    }.flowOn(ioDispatcher)

    override fun getCocktailDetails(id: Int): Flow<Resource<CocktailDetails>> = flow {
        emit(
            Resource.Success(
                demoNetworkDataSource.getCocktailDetails(id).toModel()
            )
        )
    }.flowOn(ioDispatcher)

}