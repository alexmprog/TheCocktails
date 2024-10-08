package com.alexmprog.thecocktails.core.data.repository

import com.alexmprog.thecocktails.core.common.dispatchers.CommonDispatchers
import com.alexmprog.thecocktails.core.common.dispatchers.Dispatcher
import com.alexmprog.thecocktails.core.domain.model.Cocktail
import com.alexmprog.thecocktails.core.domain.model.CocktailDetails
import com.alexmprog.thecocktails.core.domain.model.CocktailsSearchSource
import com.alexmprog.thecocktails.core.common.model.ErrorType
import com.alexmprog.thecocktails.core.common.model.Resource
import com.alexmprog.thecocktails.core.domain.repository.CocktailsRepository
import com.alexmprog.thecocktails.core.network.NetworkDataSource
import com.alexmprog.thecocktails.core.network.model.CocktailDTO
import com.alexmprog.thecocktails.core.network.model.CocktailDetailsDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class OnlineCocktailsRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    @Dispatcher(CommonDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : CocktailsRepository {

    override fun getCocktailsBySource(
        id: String,
        source: CocktailsSearchSource
    ): Flow<Resource<List<Cocktail>>> = flow {
        emit(
            when (source) {
                CocktailsSearchSource.Category -> networkDataSource.getCocktailsByCategory(id)
                CocktailsSearchSource.Ingredient -> networkDataSource.getCocktailsByIngredient(id)
                CocktailsSearchSource.Glass -> networkDataSource.getCocktailsByGlass(id)
            }
        )
    }.map { Resource.Success(it.map { it.toModel() }) as Resource<List<Cocktail>> }
        .catch { emit(Resource.Error(ErrorType.Network)) }
        .flowOn(ioDispatcher)

    override fun getCocktailDetails(id: Int): Flow<Resource<CocktailDetails>> = flow {
        emit(networkDataSource.getCocktailDetails(id))
    }.map { Resource.Success(it.toModel()) as Resource<CocktailDetails> }
        .catch { emit(Resource.Error(ErrorType.Network)) }
        .flowOn(ioDispatcher)

}

fun CocktailDTO.toModel(): Cocktail = Cocktail(id, name, image)
fun CocktailDetailsDTO.toModel(): CocktailDetails {
    val ingredients = mutableListOf<String>()
    val addMeasuredIngredient: (String?, String?) -> Unit = { ingredient, measure ->
        val value = if (!ingredient.isNullOrEmpty()) {
            if (!measure.isNullOrEmpty()) "$measure $ingredient" else ingredient
        } else null
        value?.let { ingredients.add(it) }
    }
    addMeasuredIngredient(ingredient1, measure1)
    addMeasuredIngredient(ingredient2, measure2)
    addMeasuredIngredient(ingredient3, measure3)
    addMeasuredIngredient(ingredient4, measure4)
    addMeasuredIngredient(ingredient5, measure5)
    addMeasuredIngredient(ingredient6, measure6)
    addMeasuredIngredient(ingredient7, measure7)
    addMeasuredIngredient(ingredient8, measure8)
    addMeasuredIngredient(ingredient9, measure9)
    addMeasuredIngredient(ingredient10, measure10)
    addMeasuredIngredient(ingredient11, measure11)
    addMeasuredIngredient(ingredient12, measure12)
    addMeasuredIngredient(ingredient13, measure13)
    addMeasuredIngredient(ingredient14, measure14)
    addMeasuredIngredient(ingredient15, measure15)
    return CocktailDetails(
        id,
        category,
        glass,
        description,
        ingredients
    )
}
