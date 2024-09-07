package com.alexmprog.thecocktails.core.data.repository

import com.alexmprog.thecocktails.core.common.CommonDispatchers
import com.alexmprog.thecocktails.core.common.Dispatcher
import com.alexmprog.thecocktails.core.model.Cocktail
import com.alexmprog.thecocktails.core.model.CocktailDetails
import com.alexmprog.thecocktails.core.model.CocktailsSearchSource
import com.alexmprog.thecocktails.core.network.NetworkDataSource
import com.alexmprog.thecocktails.core.network.model.CocktailDTO
import com.alexmprog.thecocktails.core.network.model.CocktailDetailsDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

interface CocktailsRepository {

    fun getCocktailsBySource(id: String, source: CocktailsSearchSource): Flow<List<Cocktail>>

    fun getCocktailDetails(id: Int): Flow<CocktailDetails>
}

internal class OnlineCocktailsRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    @Dispatcher(CommonDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : CocktailsRepository {

    override fun getCocktailsBySource(id: String, source: CocktailsSearchSource) = flow {
        kotlin.runCatching {
            emit(
                when (source) {
                    CocktailsSearchSource.Category -> networkDataSource.getCocktailsByCategory(id)
                    CocktailsSearchSource.Ingredient -> networkDataSource.getCocktailsByIngredient(
                        id
                    )

                    CocktailsSearchSource.Glass -> networkDataSource.getCocktailsByGlass(id)
                }
            )
        }
    }.map { it.map { it.toModel() } }
        .flowOn(ioDispatcher)

    override fun getCocktailDetails(id: Int): Flow<CocktailDetails> = flow {
        kotlin.runCatching {
            emit(networkDataSource.getCocktailDetails(id))
        }
    }.mapNotNull { it?.toModel() }
        .flowOn(ioDispatcher)

}

internal fun CocktailDTO.toModel(): Cocktail = Cocktail(id, name, image)

internal fun CocktailDetailsDTO.toModel(): CocktailDetails {
    val ingredients = mutableListOf<String>()
    ingredient1?.let { ingredients.add(it) }
    ingredient2?.let { ingredients.add(it) }
    ingredient3?.let { ingredients.add(it) }
    ingredient4?.let { ingredients.add(it) }
    ingredient5?.let { ingredients.add(it) }
    ingredient6?.let { ingredients.add(it) }
    ingredient7?.let { ingredients.add(it) }
    ingredient8?.let { ingredients.add(it) }
    ingredient9?.let { ingredients.add(it) }
    ingredient10?.let { ingredients.add(it) }
    ingredient11?.let { ingredients.add(it) }
    ingredient12?.let { ingredients.add(it) }
    ingredient13?.let { ingredients.add(it) }
    ingredient14?.let { ingredients.add(it) }
    ingredient15?.let { ingredients.add(it) }
    return CocktailDetails(id, category, glass, description ?: "", ingredients)
}
