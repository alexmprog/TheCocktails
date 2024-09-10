package com.alexmprog.thecocktails.core.network.retrofit

import com.alexmprog.thecocktails.core.network.mock.MockServer
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import kotlin.test.Test

class RetrofitDataSourceTest {

    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var subject: RetrofitDataSource

    @Before
    fun setup() {
        subject = RetrofitDataSource(MockServer.retrofit)
    }

    @Test
    fun shouldReturnCategoriesByDefault() = testScope.runTest {
        assertTrue(subject.getCategories().isNotEmpty())
    }

    @Test
    fun shouldReturnIngredientsByDefault() = testScope.runTest {
        assertTrue(subject.getIngredients().isNotEmpty())
    }

    @Test
    fun shouldReturnGlassesByDefault() = testScope.runTest {
        assertTrue(subject.getGlasses().isNotEmpty())
    }

    @Test
    fun shouldReturnCocktailsByDefault() = testScope.runTest {
        val id = "test"
        assertTrue(subject.getCocktailsByCategory(id).isNotEmpty())
        assertTrue(subject.getCocktailsByIngredient(id).isNotEmpty())
        assertTrue(subject.getCocktailsByGlass(id).isNotEmpty())
    }

    @Test
    fun shouldReturnCocktailDetailsByDefault() = testScope.runTest {
        val id = 1
        assertTrue(subject.getCocktailDetails(id) != null)
    }
}