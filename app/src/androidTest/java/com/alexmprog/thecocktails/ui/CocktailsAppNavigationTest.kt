package com.alexmprog.thecocktails.ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.test.espresso.Espresso
import com.alexmprog.thecocktails.MainActivity
import com.alexmprog.thecocktails.R
import com.alexmprog.thecocktails.core.testing.rules.GrantPostNotificationsPermissionRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
@HiltAndroidTest
class CocktailsAppNavigationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val postNotificationsPermission = GrantPostNotificationsPermissionRule()

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() = hiltRule.inject()

    // TODO: read this data directly from demo repositories
    private val testCategoryName = "Cocktail"
    private val testCocktailName = "155 Belmont"
    private val testIngredientName = "Light rum"
    private val testGlassName = "Highball glass"

    @Test
    fun homeNavigation_checkAllTabs() {
        composeTestRule.apply {
            var matcher = tabMatcher(activity.getString(R.string.categories))
            onNode(matcher).assertIsSelected()
            matcher = tabMatcher(activity.getString(R.string.ingredients))
            onNode(matcher).performClick().assertIsSelected()
            matcher = tabMatcher(activity.getString(R.string.glasses))
            onNode(matcher).performClick().assertIsSelected()
            Espresso.pressBack()
            matcher = tabMatcher(activity.getString(R.string.categories))
            onNode(matcher).assertExists()
        }
    }

    @Test
    fun fromCategoriesScreen_toCocktailDetailsScreen_flow() {
        composeTestRule.apply {
            onNodeWithText(testCategoryName).performScrollTo().performClick()
            onNodeWithText(testCocktailName).performScrollTo().performClick()
            waitUntilAtLeastOneExists(textMatcher(testCocktailName), 1000L)
        }
    }

    @Test
    fun fromIngredientsScreen_toCocktailDetailsScreen_flow() {
        composeTestRule.apply {
            val matcher = tabMatcher(activity.getString(R.string.ingredients))
            onNode(matcher).performClick().assertIsSelected()
            onNodeWithText(testIngredientName).performScrollTo().performClick()
            onNodeWithText(testCocktailName).performScrollTo().performClick()
            waitUntilAtLeastOneExists(textMatcher(testCocktailName), 1000L)
        }
    }

    @Test
    fun fromGlassesScreen_toCocktailDetailsScreen_flow() {
        composeTestRule.apply {
            val matcher = tabMatcher(activity.getString(R.string.glasses))
            onNode(matcher).performClick().assertIsSelected()
            onNodeWithText(testGlassName).performScrollTo().performClick()
            onNodeWithText(testCocktailName).performScrollTo().performClick()
            waitUntilAtLeastOneExists(textMatcher(testCocktailName), 1000L)
        }
    }

}