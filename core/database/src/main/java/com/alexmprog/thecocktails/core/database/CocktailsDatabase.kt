package com.alexmprog.thecocktails.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexmprog.thecocktails.core.database.dao.CategoryDao
import com.alexmprog.thecocktails.core.database.dao.GlassDao
import com.alexmprog.thecocktails.core.database.dao.IngredientDao
import com.alexmprog.thecocktails.core.database.model.CategoryEntity
import com.alexmprog.thecocktails.core.database.model.GlassEntity
import com.alexmprog.thecocktails.core.database.model.IngredientEntity

@Database(
    entities = [
        CategoryEntity::class,
        IngredientEntity::class,
        GlassEntity::class
    ],
    version = 1,
    autoMigrations = [],
    exportSchema = true,
)
internal abstract class CocktailsDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun ingredientDao(): IngredientDao

    abstract fun glassDao(): GlassDao
}