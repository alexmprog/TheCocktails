package com.alexmprog.thecocktails.core.network.demo

import JvmUnitTestDemoAssetManager
import com.alexmprog.thecocktails.core.common.CommonDispatchers
import com.alexmprog.thecocktails.core.common.Dispatcher
import com.alexmprog.thecocktails.core.network.NetworkDataSource
import com.alexmprog.thecocktails.core.network.model.CategoriesResponse
import com.alexmprog.thecocktails.core.network.model.CategoryDTO
import com.alexmprog.thecocktails.core.network.model.CocktailDTO
import com.alexmprog.thecocktails.core.network.model.CocktailDetailsDTO
import com.alexmprog.thecocktails.core.network.model.CocktailDetailsResponse
import com.alexmprog.thecocktails.core.network.model.CocktailsResponse
import com.alexmprog.thecocktails.core.network.model.GlassDTO
import com.alexmprog.thecocktails.core.network.model.GlassesResponse
import com.alexmprog.thecocktails.core.network.model.IngredientDTO
import com.alexmprog.thecocktails.core.network.model.IngredientsResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.serializer
import javax.inject.Inject

@OptIn(ExperimentalSerializationApi::class, InternalSerializationApi::class)
class DemoNetworkDataSource @Inject constructor(
    @Dispatcher(CommonDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: DemoAssetManager = JvmUnitTestDemoAssetManager,
) : NetworkDataSource {

    override suspend fun getCategories(): List<CategoryDTO> = withContext(ioDispatcher) {
        assets.open("categories.json").use {
            networkJson.decodeFromStream(CategoriesResponse::class.serializer(), it).categories
        }
    }

    override suspend fun getIngredients(): List<IngredientDTO> = withContext(ioDispatcher) {
        assets.open("ingredients.json").use {
            networkJson.decodeFromStream(IngredientsResponse::class.serializer(), it).ingredients
        }
    }

    override suspend fun getGlasses(): List<GlassDTO> = withContext(ioDispatcher) {
        assets.open("glasses.json").use {
            networkJson.decodeFromStream(GlassesResponse::class.serializer(), it).glasses
        }
    }

    override suspend fun getCocktailsByCategory(category: String): List<CocktailDTO> =
        withContext(ioDispatcher) {
            assets.open("cocktails.json").use {
                networkJson.decodeFromStream(CocktailsResponse::class.serializer(), it).cocktails
            }
        }

    override suspend fun getCocktailsByIngredient(ingredient: String): List<CocktailDTO> =
        assets.open("cocktails.json").use {
            networkJson.decodeFromStream(CocktailsResponse::class.serializer(), it).cocktails
        }

    override suspend fun getCocktailsByGlass(glass: String): List<CocktailDTO> =
        assets.open("cocktails.json").use {
            networkJson.decodeFromStream(CocktailsResponse::class.serializer(), it).cocktails
        }

    override suspend fun getCocktailDetails(id: Int): CocktailDetailsDTO =
        withContext(ioDispatcher) {
            assets.open("cocktail-details.json").use {
                networkJson.decodeFromStream(
                    CocktailDetailsResponse::class.serializer(),
                    it
                ).cocktails.first()
            }
        }
}