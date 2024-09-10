package com.alexmprog.thecocktails.core.network.demo

import JvmUnitTestDemoAssetManager
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Assert.assertTrue
import org.junit.Before
import kotlin.test.Test

class DemoNetworkDataSourceTest {

    private lateinit var subject: DemoNetworkDataSource

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        subject = DemoNetworkDataSource(
            ioDispatcher = testDispatcher,
            networkJson = Json { ignoreUnknownKeys = true },
            assets = JvmUnitTestDemoAssetManager,
        )
    }

    @Test
    fun shouldReturnCategoriesByDefault() = runTest(testDispatcher) {
        assertTrue(subject.getCategories().isNotEmpty())
    }

    @Test
    fun shouldReturnIngredientsByDefault() = runTest(testDispatcher) {
        assertTrue(subject.getIngredients().isNotEmpty())
    }

    @Test
    fun shouldReturnGlassesByDefault() = runTest(testDispatcher) {
        assertTrue(subject.getGlasses().isNotEmpty())
    }

    @Test
    fun shouldReturnCocktailsByDefault() = runTest(testDispatcher) {
        val id = "test"
        assertTrue(subject.getCocktailsByCategory(id).isNotEmpty())
        assertTrue(subject.getCocktailsByIngredient(id).isNotEmpty())
        assertTrue(subject.getCocktailsByGlass(id).isNotEmpty())
    }

    @Test
    fun shouldReturnCocktailDetailsByDefault() = runTest(testDispatcher) {
        val id = 1
        assertTrue(subject.getCocktailDetails(id) != null)
    }

}