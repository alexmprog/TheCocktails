package com.alexmprog.thecocktails.core.network.retrofit

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
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

internal interface NetworkService {

    @GET("list.php?c=list")
    suspend fun getCategories(): CategoriesResponse

    @GET("list.php?i=list")
    suspend fun getIngredients(): IngredientsResponse

    @GET("list.php?g=list")
    suspend fun getGlasses(): GlassesResponse

    @GET("filter.php")
    suspend fun getCocktailsByCategory(@Query("c") category: String): CocktailsResponse

    @GET("filter.php")
    suspend fun getCocktailsByIngredient(@Query("i") ingredient: String): CocktailsResponse

    @GET("filter.php")
    suspend fun getCocktailsByGlass(@Query("g") glass: String): CocktailsResponse

    @GET("lookup.php")
    suspend fun getCocktailDetails(@Query("i") id: Int): CocktailDetailsResponse
}

internal class RetrofitDataSource(retrofit: Retrofit) : NetworkDataSource {

    private val networkService: NetworkService = retrofit.create(NetworkService::class.java)

    override suspend fun getCategories(): List<CategoryDTO> =
        networkService.getCategories().categories

    override suspend fun getIngredients(): List<IngredientDTO> =
        networkService.getIngredients().ingredients

    override suspend fun getGlasses(): List<GlassDTO> =
        networkService.getGlasses().glasses

    override suspend fun getCocktailsByCategory(category: String): List<CocktailDTO> =
        networkService.getCocktailsByCategory(category).cocktails

    override suspend fun getCocktailsByIngredient(ingredient: String): List<CocktailDTO> =
        networkService.getCocktailsByIngredient(ingredient).cocktails

    override suspend fun getCocktailsByGlass(glass: String): List<CocktailDTO> =
        networkService.getCocktailsByGlass(glass).cocktails

    override suspend fun getCocktailDetails(id: Int): CocktailDetailsDTO =
        networkService.getCocktailDetails(id).cocktails.first()
}