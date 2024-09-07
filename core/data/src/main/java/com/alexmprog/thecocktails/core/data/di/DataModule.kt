package com.alexmprog.thecocktails.core.data.di

import com.alexmprog.thecocktails.core.data.repository.CategoriesRepository
import com.alexmprog.thecocktails.core.data.repository.CocktailsRepository
import com.alexmprog.thecocktails.core.data.repository.GlassesRepository
import com.alexmprog.thecocktails.core.data.repository.IngredientsRepository
import com.alexmprog.thecocktails.core.data.repository.OfflineFirstCategoriesRepository
import com.alexmprog.thecocktails.core.data.repository.OnlineCocktailsRepository
import com.alexmprog.thecocktails.core.data.repository.OfflineFirstGlassesRepository
import com.alexmprog.thecocktails.core.data.repository.OfflineFirstIngredientsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindsCategoriesRepository(
        categoriesRepository: OfflineFirstCategoriesRepository,
    ): CategoriesRepository

    @Binds
    @Singleton
    abstract fun bindsIngredientsRepository(
        ingredientsRepository: OfflineFirstIngredientsRepository,
    ): IngredientsRepository

    @Binds
    @Singleton
    abstract fun bindsCocktailsRepository(
        cocktailsRepository: OnlineCocktailsRepository,
    ): CocktailsRepository

    @Binds
    @Singleton
    abstract fun bindsGlassesRepository(
        glassesRepository: OfflineFirstGlassesRepository,
    ): GlassesRepository

}