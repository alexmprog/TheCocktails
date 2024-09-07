package com.alexmprog.thecocktails.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Glass")
data class GlassEntity(
    @PrimaryKey
    val name: String
)