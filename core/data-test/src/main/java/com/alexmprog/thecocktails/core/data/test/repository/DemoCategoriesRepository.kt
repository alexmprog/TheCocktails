package com.alexmprog.thecocktails.core.data.test.repository

import com.alexmprog.thecocktails.core.common.CommonDispatchers
import com.alexmprog.thecocktails.core.common.Dispatcher
import com.alexmprog.thecocktails.core.data.repository.CategoriesRepository
import com.alexmprog.thecocktails.core.data.repository.toEntity
import com.alexmprog.thecocktails.core.data.repository.toModel
import com.alexmprog.thecocktails.core.model.Category
import com.alexmprog.thecocktails.core.model.Resource
import com.alexmprog.thecocktails.core.network.demo.DemoNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DemoCategoriesRepository @Inject constructor(
    private val demoNetworkDataSource: DemoNetworkDataSource,
    @Dispatcher(CommonDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : CategoriesRepository {

    override fun getCategories(): Flow<Resource<List<Category>>> = flow {
        emit(
            Resource.Success(demoNetworkDataSource.getCategories().map { it.toEntity().toModel() })
        )
    }.flowOn(ioDispatcher)

}