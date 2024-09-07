package com.alexmprog.thecocktails.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CategoriesResponse(
    @SerialName("drinks")
    val categories: List<CategoryDTO>
)

@Serializable
data class CategoryDTO(
    @SerialName("strCategory")
    val name: String
)