package com.alexmprog.thecocktails.core.network

import com.alexmprog.thecocktails.core.network.model.CategoryDTO
import com.alexmprog.thecocktails.core.network.model.CocktailDTO
import com.alexmprog.thecocktails.core.network.model.CocktailDetailsDTO
import com.alexmprog.thecocktails.core.network.model.GlassDTO
import com.alexmprog.thecocktails.core.network.model.IngredientDTO

interface NetworkDataSource {

    suspend fun getCategories(): List<CategoryDTO>

    suspend fun getIngredients(): List<IngredientDTO>

    suspend fun getGlasses(): List<GlassDTO>

    suspend fun getCocktailsByCategory(category: String): List<CocktailDTO>

    suspend fun getCocktailsByIngredient(ingredient: String): List<CocktailDTO>

    suspend fun getCocktailsByGlass(glass: String): List<CocktailDTO>

    suspend fun getCocktailDetails(id: Int): CocktailDetailsDTO?

}