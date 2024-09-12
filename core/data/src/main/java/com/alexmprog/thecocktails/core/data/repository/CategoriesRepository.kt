package com.alexmprog.thecocktails.core.data.repository

import com.alexmprog.thecocktails.core.common.CommonDispatchers
import com.alexmprog.thecocktails.core.common.Dispatcher
import com.alexmprog.thecocktails.core.database.dao.CategoryDao
import com.alexmprog.thecocktails.core.database.model.CategoryEntity
import com.alexmprog.thecocktails.core.model.Category
import com.alexmprog.thecocktails.core.model.ErrorType
import com.alexmprog.thecocktails.core.model.Resource
import com.alexmprog.thecocktails.core.network.NetworkDataSource
import com.alexmprog.thecocktails.core.network.model.CategoryDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface CategoriesRepository {

    fun getCategories(): Flow<Resource<List<Category>>>
}

internal class OfflineFirstCategoriesRepository @Inject constructor(
    private val categoryDao: CategoryDao,
    private val networkDataSource: NetworkDataSource,
    @Dispatcher(CommonDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : CategoriesRepository {

    override fun getCategories(): Flow<Resource<List<Category>>> = flow {
        val localFlow = categoryDao.getCategoryEntities()
        runCatching {
            categoryDao.saveCategoryEntities(
                networkDataSource.getCategories().map { it.toEntity() }
            )
        }.onSuccess {
            emit(localFlow.first())
        }.onFailure {
            val localData = localFlow.firstOrNull()
            if (!localData.isNullOrEmpty()) emit(localData) else throw it
        }
    }.map { it -> Resource.Success(it.map { it.toModel() }) as Resource<List<Category>> }
        .catch { emit(Resource.Error(ErrorType.Network)) }
        .flowOn(ioDispatcher)
}

internal fun CategoryDTO.toEntity(): CategoryEntity = CategoryEntity(name)

internal fun CategoryEntity.toModel(): Category = Category(name)
