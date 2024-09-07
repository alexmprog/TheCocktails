package com.alexmprog.thecocktails.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Category")
data class CategoryEntity(
    @PrimaryKey
    val name: String
)