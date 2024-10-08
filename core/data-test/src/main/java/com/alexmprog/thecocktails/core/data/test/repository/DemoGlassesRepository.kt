package com.alexmprog.thecocktails.core.data.test.repository

import com.alexmprog.thecocktails.core.common.dispatchers.CommonDispatchers
import com.alexmprog.thecocktails.core.common.dispatchers.Dispatcher
import com.alexmprog.thecocktails.core.data.repository.toEntity
import com.alexmprog.thecocktails.core.data.repository.toModel
import com.alexmprog.thecocktails.core.common.model.Resource
import com.alexmprog.thecocktails.core.domain.model.Glass
import com.alexmprog.thecocktails.core.domain.repository.GlassesRepository
import com.alexmprog.thecocktails.core.network.demo.DemoNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DemoGlassesRepository @Inject constructor(
    private val demoNetworkDataSource: DemoNetworkDataSource,
    @Dispatcher(CommonDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : GlassesRepository {

    override fun getGlasses(): Flow<Resource<List<Glass>>> = flow {
        emit(
            Resource.Success(
                demoNetworkDataSource.getGlasses().map { it.toEntity().toModel() })
        )
    }.flowOn(ioDispatcher)

}