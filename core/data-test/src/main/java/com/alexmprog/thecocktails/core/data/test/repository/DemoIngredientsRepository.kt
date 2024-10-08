package com.alexmprog.thecocktails.core.data.test.repository

import com.alexmprog.thecocktails.core.common.dispatchers.CommonDispatchers
import com.alexmprog.thecocktails.core.common.dispatchers.Dispatcher
import com.alexmprog.thecocktails.core.data.repository.toEntity
import com.alexmprog.thecocktails.core.data.repository.toModel
import com.alexmprog.thecocktails.core.domain.model.Ingredient
import com.alexmprog.thecocktails.core.common.model.Resource
import com.alexmprog.thecocktails.core.domain.repository.IngredientsRepository
import com.alexmprog.thecocktails.core.network.demo.DemoNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DemoIngredientsRepository @Inject constructor(
    private val demoNetworkDataSource: DemoNetworkDataSource,
    @Dispatcher(CommonDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : IngredientsRepository {

    override fun getIngredients(): Flow<Resource<List<Ingredient>>> = flow {
        emit(
            Resource.Success(
                demoNetworkDataSource.getIngredients().map { it.toEntity().toModel() })
        )
    }.flowOn(ioDispatcher)

}