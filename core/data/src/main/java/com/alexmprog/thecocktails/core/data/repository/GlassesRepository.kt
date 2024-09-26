package com.alexmprog.thecocktails.core.data.repository

import com.alexmprog.thecocktails.core.common.CommonDispatchers
import com.alexmprog.thecocktails.core.common.Dispatcher
import com.alexmprog.thecocktails.core.database.dao.GlassDao
import com.alexmprog.thecocktails.core.database.model.GlassEntity
import com.alexmprog.thecocktails.core.model.ErrorType
import com.alexmprog.thecocktails.core.model.Glass
import com.alexmprog.thecocktails.core.model.Resource
import com.alexmprog.thecocktails.core.network.NetworkDataSource
import com.alexmprog.thecocktails.core.network.model.GlassDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GlassesRepository {

    fun getGlasses(): Flow<Resource<List<Glass>>>
}

internal class OfflineFirstGlassesRepository @Inject constructor(
    private val glassDao: GlassDao,
    private val networkDataSource: NetworkDataSource,
    @Dispatcher(CommonDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : GlassesRepository {

    override fun getGlasses(): Flow<Resource<List<Glass>>> = flow {
        val localFlow =glassDao.getGlassEntities()
        runCatching {
            glassDao.saveGlassEntities(
                networkDataSource.getGlasses().map { it.toEntity() }
            )
        }.onSuccess {
            emit(localFlow.first())
        }.onFailure {
            val localData = localFlow.firstOrNull()
            if (!localData.isNullOrEmpty()) emit(localData) else throw it
        }
    }.map { it -> Resource.Success(it.map { it.toModel() }) as Resource<List<Glass>> }
        .catch { emit(Resource.Error(ErrorType.Network)) }
        .flowOn(ioDispatcher)
}

fun GlassDTO.toEntity(): GlassEntity = GlassEntity(name)
fun GlassEntity.toModel(): Glass = Glass(name)
