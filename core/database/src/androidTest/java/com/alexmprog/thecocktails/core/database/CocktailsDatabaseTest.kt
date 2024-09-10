package com.alexmprog.thecocktails.core.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.alexmprog.thecocktails.core.database.dao.CategoryDao
import com.alexmprog.thecocktails.core.database.dao.GlassDao
import com.alexmprog.thecocktails.core.database.dao.IngredientDao
import com.alexmprog.thecocktails.core.database.model.CategoryEntity
import com.alexmprog.thecocktails.core.database.model.GlassEntity
import com.alexmprog.thecocktails.core.database.model.IngredientEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

// split into multiple tests later
class CocktailsDatabaseTest {

    private lateinit var database: CocktailsDatabase
    private lateinit var categoryDao: CategoryDao
    private lateinit var ingredientDao: IngredientDao
    private lateinit var glassDao: GlassDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            CocktailsDatabase::class.java,
        ).build()
        categoryDao = database.categoryDao()
        ingredientDao = database.ingredientDao()
        glassDao = database.glassDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun showReturnEmptyCategoriesListByDefault() = runTest {
        assertTrue(categoryDao.getCategoryEntities().first().isEmpty())
    }

    @Test
    fun showReturnCategoriesListAfterInsert() = runTest {
        val entities = listOf(
            CategoryEntity("tes1"),
            CategoryEntity("test2"),
            CategoryEntity("test3")
        )
        categoryDao.saveCategoryEntities(entities)
        assertEquals(categoryDao.getCategoryEntities().first().size, entities.size)
    }

    @Test
    fun showReturnEmptyIngredientsListByDefault() = runTest {
        assertTrue(ingredientDao.getIngredientEntities().first().isEmpty())
    }

    @Test
    fun showReturnIngredientsListAfterInsert() = runTest {
        val entities = listOf(
            IngredientEntity("tes1"),
            IngredientEntity("test2"),
            IngredientEntity("test3")
        )
        ingredientDao.saveIngredientEntities(entities)
        assertEquals(ingredientDao.getIngredientEntities().first().size, entities.size)
    }

    @Test
    fun showReturnEmptyGlassesListByDefault() = runTest {
        assertTrue(glassDao.getGlassEntities().first().isEmpty())
    }

    @Test
    fun showReturnGlassesListAfterInsert() = runTest {
        val entities = listOf(
            GlassEntity("tes1"),
            GlassEntity("test2"),
            GlassEntity("test3")
        )
        glassDao.saveGlassEntities(entities)
        assertEquals(glassDao.getGlassEntities().first().size, entities.size)
    }
}