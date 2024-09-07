package com.alexmprog.thecocktails.core.data.repository

import com.alexmprog.thecocktails.core.common.CommonDispatchers
import com.alexmprog.thecocktails.core.common.Dispatcher
import com.alexmprog.thecocktails.core.database.dao.IngredientDao
import com.alexmprog.thecocktails.core.database.model.IngredientEntity
import com.alexmprog.thecocktails.core.model.Ingredient
import com.alexmprog.thecocktails.core.network.NetworkDataSource
import com.alexmprog.thecocktails.core.network.model.IngredientDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface IngredientsRepository {

    fun getIngredients(): Flow<List<Ingredient>>
}

internal class OfflineFirstIngredientsRepository @Inject constructor(
    private val ingredientDao: IngredientDao,
    private val networkDataSource: NetworkDataSource,
    @Dispatcher(CommonDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : IngredientsRepository {

    override fun getIngredients(): Flow<List<Ingredient>> = networkBoundedFlow(
        local = ingredientDao.getIngredientEntities(),
        remote = { networkDataSource.getIngredients() },
        save = { ingredientDao.saveIngredientEntities(it.map { it.toEntity() } )}
    ).map { it.map { it.toModel() } }
        .flowOn(ioDispatcher)
}

private fun IngredientDTO.toEntity(): IngredientEntity = IngredientEntity(name)
private fun IngredientEntity.toModel(): Ingredient = Ingredient(name)
