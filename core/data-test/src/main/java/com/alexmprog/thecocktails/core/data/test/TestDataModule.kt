package com.alexmprog.thecocktails.core.data.test

import com.alexmprog.thecocktails.core.data.di.DataModule
import com.alexmprog.thecocktails.core.data.repository.CategoriesRepository
import com.alexmprog.thecocktails.core.data.repository.CocktailsRepository
import com.alexmprog.thecocktails.core.data.repository.GlassesRepository
import com.alexmprog.thecocktails.core.data.repository.IngredientsRepository
import com.alexmprog.thecocktails.core.data.repository.UserSettingsRepository
import com.alexmprog.thecocktails.core.data.test.repository.DemoCategoriesRepository
import com.alexmprog.thecocktails.core.data.test.repository.DemoCocktailsRepository
import com.alexmprog.thecocktails.core.data.test.repository.DemoGlassesRepository
import com.alexmprog.thecocktails.core.data.test.repository.DemoIngredientsRepository
import com.alexmprog.thecocktails.core.data.test.repository.DemoUserSettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class],
)
abstract class TestDataModule {

    @Binds
    @Singleton
    internal abstract fun bindsCategoriesRepository(
        categoriesRepository: DemoCategoriesRepository,
    ): CategoriesRepository

    @Binds
    @Singleton
    internal abstract fun bindsIngredientsRepository(
        ingredientsRepository: DemoIngredientsRepository,
    ): IngredientsRepository

    @Binds
    @Singleton
    internal abstract fun bindsCocktailsRepository(
        cocktailsRepository: DemoCocktailsRepository,
    ): CocktailsRepository

    @Binds
    @Singleton
    internal abstract fun bindsGlassesRepository(
        glassesRepository: DemoGlassesRepository,
    ): GlassesRepository

    @Binds
    @Singleton
    internal abstract fun bindsUserSettingsRepository(
        userSettingsRepository: DemoUserSettingsRepository,
    ): UserSettingsRepository

}