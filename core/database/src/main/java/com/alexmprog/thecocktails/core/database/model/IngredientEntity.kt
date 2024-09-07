package com.alexmprog.thecocktails.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Ingredient")
data class IngredientEntity(
    @PrimaryKey
    val name: String
)