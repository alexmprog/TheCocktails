package com.alexmprog.thecocktails.core.data.test.repository

import com.alexmprog.thecocktails.core.common.CommonDispatchers
import com.alexmprog.thecocktails.core.common.Dispatcher
import com.alexmprog.thecocktails.core.data.repository.IngredientsRepository
import com.alexmprog.thecocktails.core.data.repository.toEntity
import com.alexmprog.thecocktails.core.data.repository.toModel
import com.alexmprog.thecocktails.core.model.Ingredient
import com.alexmprog.thecocktails.core.model.Resource
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