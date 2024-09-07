package com.alexmprog.thecocktails.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GlassesResponse(
    @SerialName("drinks")
    val glasses: List<GlassDTO>
)

@Serializable
data class GlassDTO(
    @SerialName("strGlass")
    val name: String
)