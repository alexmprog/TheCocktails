package com.alexmprog.thecocktails.feature.glasses.list

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.alexmprog.thecocktails.core.model.Glass
import com.alexmprog.thecocktails.core.testing.rules.GrantPostNotificationsPermissionRule
import com.alexmprog.thecocktails.core.ui.state.ErrorText
import com.alexmprog.thecocktails.core.ui.state.UiState
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertEquals

class GlassesListScreenTest {

    @get:Rule(order = 0)
    val postNotificationsPermission = GrantPostNotificationsPermissionRule()

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun progressIndicator_whenScreenIsLoading_exists() {
        composeTestRule.setContent {
            Box {
                GlassesListScreen(
                    uiState = UiState.Loading,
                    onGlassClick = {}
                )
            }
        }

        val loadingViewMatcher = SemanticsMatcher.expectValue(
            SemanticsProperties.ProgressBarRangeInfo, ProgressBarRangeInfo.Indeterminate
        )

        composeTestRule
            .onNode(loadingViewMatcher)
            .assertExists()
    }

    @Test
    fun dataList_whenScreenIsSuccess_exists() {
        val testGlassName = "test_glass_name1"
        composeTestRule.setContent {
            Box {
                GlassesListScreen(
                    uiState = UiState.Success(listOf(Glass(testGlassName))),
                    onGlassClick = {}
                )
            }
        }

        composeTestRule
            .onNodeWithText(testGlassName)
            .assertExists()
    }

    @Test
    fun errorMessage_whenScreenIsError_exists() {
        val errorResId = com.alexmprog.thecocktails.core.ui.R.string.core_ui_unknown_error
        composeTestRule.setContent {
            Box {
                GlassesListScreen(
                    uiState = UiState.Error(ErrorText.StringResource(errorResId)),
                    onGlassClick = {}
                )
            }
        }

        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(errorResId))
            .assertExists()
    }

    @Test
    fun callback_whenDataIsClicked_exists() {
        var clickedItem: Glass? = null
        val testGlassName = "test_glass_name1"
        composeTestRule.setContent {
            Box {
                GlassesListScreen(
                    uiState = UiState.Success(listOf(Glass(testGlassName))),
                    onGlassClick = { clickedItem = it }
                )
            }
        }
        composeTestRule
            .onNodeWithText(testGlassName)
            .performClick()
        assertEquals(clickedItem?.name, testGlassName)
    }

}