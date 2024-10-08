package com.alexmprog.thecocktails.core.data.di

import com.alexmprog.thecocktails.core.data.repository.OfflineFirstCategoriesRepository
import com.alexmprog.thecocktails.core.data.repository.OnlineCocktailsRepository
import com.alexmprog.thecocktails.core.data.repository.OfflineFirstGlassesRepository
import com.alexmprog.thecocktails.core.data.repository.OfflineFirstIngredientsRepository
import com.alexmprog.thecocktails.core.data.repository.PrefsUserSettingsRepository
import com.alexmprog.thecocktails.core.domain.repository.CategoriesRepository
import com.alexmprog.thecocktails.core.domain.repository.CocktailsRepository
import com.alexmprog.thecocktails.core.domain.repository.GlassesRepository
import com.alexmprog.thecocktails.core.domain.repository.IngredientsRepository
import com.alexmprog.thecocktails.core.domain.repository.UserSettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    internal abstract fun bindsCategoriesRepository(
        categoriesRepository: OfflineFirstCategoriesRepository,
    ): CategoriesRepository

    @Binds
    @Singleton
    internal abstract fun bindsIngredientsRepository(
        ingredientsRepository: OfflineFirstIngredientsRepository,
    ): IngredientsRepository

    @Binds
    @Singleton
    internal abstract fun bindsCocktailsRepository(
        cocktailsRepository: OnlineCocktailsRepository,
    ): CocktailsRepository

    @Binds
    @Singleton
    internal abstract fun bindsGlassesRepository(
        glassesRepository: OfflineFirstGlassesRepository,
    ): GlassesRepository

    @Binds
    @Singleton
    internal abstract fun bindsUserSettingsRepository(
        userSettingsRepository: PrefsUserSettingsRepository,
    ): UserSettingsRepository

}