package com.alexmprog.thecocktails.core.domain.model

data class Cocktail(
    val id: Int,
    val name: String,
    val image: String?
)

data class CocktailDetails(
    val id: Int,
    val category: String,
    val glass: String,
    val description: String,
    val measuredIngredients: List<String>
)

enum class CocktailsSearchSource {
    Category, Ingredient, Glass;
}