package com.alexmprog.thecocktails.core.data.repository

import com.alexmprog.thecocktails.core.common.CommonDispatchers
import com.alexmprog.thecocktails.core.common.Dispatcher
import com.alexmprog.thecocktails.core.database.dao.CategoryDao
import com.alexmprog.thecocktails.core.database.model.CategoryEntity
import com.alexmprog.thecocktails.core.model.Category
import com.alexmprog.thecocktails.core.network.NetworkDataSource
import com.alexmprog.thecocktails.core.network.model.CategoryDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface CategoriesRepository {

    fun getCategories(): Flow<List<Category>>
}

internal class OfflineFirstCategoriesRepository @Inject constructor(
    private val categoryDao: CategoryDao,
    private val networkDataSource: NetworkDataSource,
    @Dispatcher(CommonDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : CategoriesRepository {

    override fun getCategories(): Flow<List<Category>> = networkBoundedFlow(
        local = categoryDao.getCategoryEntities(),
        remote = { networkDataSource.getCategories() },
        save = { categoryDao.saveCategoryEntities(it.map { it.toEntity() }) }
    ).map { it.map { it.toModel() } }
        .flowOn(ioDispatcher)
}

internal fun CategoryDTO.toEntity(): CategoryEntity = CategoryEntity(name)

internal fun CategoryEntity.toModel(): Category = Category(name)
