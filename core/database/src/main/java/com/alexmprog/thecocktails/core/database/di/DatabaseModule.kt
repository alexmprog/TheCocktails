package com.alexmprog.thecocktails.core.database.di

import android.content.Context
import androidx.room.Room
import com.alexmprog.thecocktails.core.database.CocktailsDatabase
import com.alexmprog.thecocktails.core.database.dao.CategoryDao
import com.alexmprog.thecocktails.core.database.dao.GlassDao
import com.alexmprog.thecocktails.core.database.dao.IngredientDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun providesCocktailsDatabase(@ApplicationContext context: Context): CocktailsDatabase =
        Room.databaseBuilder(
            context,
            CocktailsDatabase::class.java,
            "cocktails-database",
        ).build()

    @Provides
    fun providesCategoryDao(database: CocktailsDatabase): CategoryDao = database.categoryDao()

    @Provides
    fun providesIngredientDao(database: CocktailsDatabase): IngredientDao = database.ingredientDao()

    @Provides
    fun providesGlassDao(database: CocktailsDatabase): GlassDao = database.glassDao()
}