package com.alexmprog.thecocktails.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CocktailsResponse(
    @SerialName("drinks")
    val cocktails: List<CocktailDTO>
)

@Serializable
internal data class CocktailDetailsResponse(
    @SerialName("drinks")
    val cocktails: List<CocktailDetailsDTO>
)

@Serializable
data class CocktailDTO(
    @SerialName("idDrink")
    val id: Int,
    @SerialName("strDrink")
    val name: String,
    @SerialName("strDrinkThumb")
    val image: String?
)

@Serializable
data class CocktailDetailsDTO(
    @SerialName("idDrink")
    val id: Int,
    @SerialName("strCategory")
    val category: String,
    @SerialName("strGlass")
    val glass: String,
    @SerialName("strInstructions")
    val description: String,
    @SerialName("strIngredient1")
    val ingredient1: String?,
    @SerialName("strIngredient2")
    val ingredient2: String?,
    @SerialName("strIngredient3")
    val ingredient3: String?,
    @SerialName("strIngredient4")
    val ingredient4: String?,
    @SerialName("strIngredient5")
    val ingredient5: String?,
    @SerialName("strIngredient6")
    val ingredient6: String?,
    @SerialName("strIngredient7")
    val ingredient7: String?,
    @SerialName("strIngredient8")
    val ingredient8: String?,
    @SerialName("strIngredient9")
    val ingredient9: String?,
    @SerialName("strIngredient10")
    val ingredient10: String?,
    @SerialName("strIngredient11")
    val ingredient11: String?,
    @SerialName("strIngredient12")
    val ingredient12: String?,
    @SerialName("strIngredient13")
    val ingredient13: String?,
    @SerialName("strIngredient14")
    val ingredient14: String?,
    @SerialName("strIngredient15")
    val ingredient15: String?,
    @SerialName("strMeasure1")
    val measure1: String?,
    @SerialName("strMeasure2")
    val measure2: String?,
    @SerialName("strMeasure3")
    val measure3: String?,
    @SerialName("strMeasure4")
    val measure4: String?,
    @SerialName("strMeasure5")
    val measure5: String?,
    @SerialName("strMeasure6")
    val measure6: String?,
    @SerialName("strMeasure7")
    val measure7: String?,
    @SerialName("strMeasure8")
    val measure8: String?,
    @SerialName("strMeasure9")
    val measure9: String?,
    @SerialName("strMeasure10")
    val measure10: String?,
    @SerialName("strMeasure11")
    val measure11: String?,
    @SerialName("strMeasure12")
    val measure12: String?,
    @SerialName("strMeasure13")
    val measure13: String?,
    @SerialName("strMeasure14")
    val measure14: String?,
    @SerialName("strMeasure15")
    val measure15: String?
)