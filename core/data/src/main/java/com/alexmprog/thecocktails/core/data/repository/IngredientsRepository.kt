package com.alexmprog.thecocktails.core.data.repository

import com.alexmprog.thecocktails.core.common.CommonDispatchers
import com.alexmprog.thecocktails.core.common.Dispatcher
import com.alexmprog.thecocktails.core.database.dao.IngredientDao
import com.alexmprog.thecocktails.core.database.model.IngredientEntity
import com.alexmprog.thecocktails.core.model.ErrorType
import com.alexmprog.thecocktails.core.model.Ingredient
import com.alexmprog.thecocktails.core.model.Resource
import com.alexmprog.thecocktails.core.network.NetworkDataSource
import com.alexmprog.thecocktails.core.network.model.IngredientDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface IngredientsRepository {

    fun getIngredients(): Flow<Resource<List<Ingredient>>>
}

internal class OfflineFirstIngredientsRepository @Inject constructor(
    private val ingredientDao: IngredientDao,
    private val networkDataSource: NetworkDataSource,
    @Dispatcher(CommonDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : IngredientsRepository {

    override fun getIngredients(): Flow<Resource<List<Ingredient>>> = flow {
        val localFlow = ingredientDao.getIngredientEntities()
        runCatching {
            ingredientDao.saveIngredientEntities(
                networkDataSource.getIngredients().map { it.toEntity() }
            )
        }.onSuccess {
            emit(localFlow.first())
        }.onFailure {
            val localData = localFlow.firstOrNull()
            if (!localData.isNullOrEmpty()) emit(localData) else throw it
        }
    }.map { it -> Resource.Success(it.map { it.toModel() }) as Resource<List<Ingredient>> }
        .catch { emit(Resource.Error(ErrorType.Network)) }
        .flowOn(ioDispatcher)
}

private fun IngredientDTO.toEntity(): IngredientEntity = IngredientEntity(name)
private fun IngredientEntity.toModel(): Ingredient = Ingredient(name)
