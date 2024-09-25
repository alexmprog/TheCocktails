package com.alexmprog.thecocktails.feature.categories.list

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.alexmprog.thecocktails.categories.list.CategoriesListScreen
import com.alexmprog.thecocktails.core.model.Category
import com.alexmprog.thecocktails.core.ui.state.ErrorText
import com.alexmprog.thecocktails.core.ui.state.UiState
import org.junit.Rule
import kotlin.test.Test

class CategoriesScreenListTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun progressIndicator_whenScreenIsLoading_exists() {
        composeTestRule.setContent {
            Box {
                CategoriesListScreen(
                    uiState = UiState.Loading,
                    onCategoryClick = {}
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
        val testCategoryName = "test_category_name1"
        composeTestRule.setContent {
            Box {
                CategoriesListScreen(
                    uiState = UiState.Success(listOf(Category(testCategoryName))),
                    onCategoryClick = {}
                )
            }
        }

        composeTestRule
            .onNodeWithText(testCategoryName)
            .assertExists()
    }

    @Test
    fun errorMessage_whenScreenIsError_exists() {
        val errorResId = com.alexmprog.thecocktails.core.ui.R.string.core_ui_unknown_error
        composeTestRule.setContent {
            Box {
                CategoriesListScreen(
                    uiState = UiState.Error(ErrorText.StringResource(errorResId)),
                    onCategoryClick = {}
                )
            }
        }

        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(errorResId))
            .assertExists()
    }

}