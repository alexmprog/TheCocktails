package com.alexmprog.thecocktails.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class IngredientsResponse(
    @SerialName("drinks")
    val ingredients: List<IngredientDTO>
)

@Serializable
data class IngredientDTO(
    @SerialName("strIngredient1")
    val name: String
)