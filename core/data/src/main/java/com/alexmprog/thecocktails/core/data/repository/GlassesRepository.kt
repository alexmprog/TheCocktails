package com.alexmprog.thecocktails.core.data.repository

import com.alexmprog.thecocktails.core.common.CommonDispatchers
import com.alexmprog.thecocktails.core.common.Dispatcher
import com.alexmprog.thecocktails.core.database.dao.GlassDao
import com.alexmprog.thecocktails.core.database.model.GlassEntity
import com.alexmprog.thecocktails.core.model.Glass
import com.alexmprog.thecocktails.core.network.NetworkDataSource
import com.alexmprog.thecocktails.core.network.model.GlassDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GlassesRepository {

    fun getGlasses(): Flow<List<Glass>>
}

internal class OfflineFirstGlassesRepository @Inject constructor(
    private val glassDao: GlassDao,
    private val networkDataSource: NetworkDataSource,
    @Dispatcher(CommonDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : GlassesRepository {

    override fun getGlasses(): Flow<List<Glass>> = networkBoundedFlow(
        local = glassDao.getGlassEntities(),
        remote = { networkDataSource.getGlasses() },
        save = { glassDao.saveGlassEntities(it.map { it.toEntity() }) }
    ).map { it.map { it.toModel() } }
        .flowOn(ioDispatcher)
}

internal fun GlassDTO.toEntity(): GlassEntity = GlassEntity(name)

internal fun GlassEntity.toModel(): Glass = Glass(name)
