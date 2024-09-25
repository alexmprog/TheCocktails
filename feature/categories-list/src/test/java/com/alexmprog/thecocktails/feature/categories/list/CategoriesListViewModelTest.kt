package com.alexmprog.thecocktails.feature.categories.list

import com.alexmprog.thecocktails.categories.list.CategoriesListViewModel
import com.alexmprog.thecocktails.core.domain.GetCategoriesUseCase
import com.alexmprog.thecocktails.core.model.Category
import com.alexmprog.thecocktails.core.model.ErrorType
import com.alexmprog.thecocktails.core.model.Resource
import com.alexmprog.thecocktails.core.testing.repository.TestCategoriesRepository
import com.alexmprog.thecocktails.core.testing.rules.TestDispatcherRule
import com.alexmprog.thecocktails.core.ui.state.UiState
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CategoriesListViewModelTest {

    @get:Rule
    val mainDispatcherRule = TestDispatcherRule()

    private val categoriesRepository = TestCategoriesRepository()

    private lateinit var viewModel: CategoriesListViewModel

    @Before
    fun setup() {
        viewModel = CategoriesListViewModel(
            GetCategoriesUseCase(categoriesRepository)
        )
    }

    @Test
    fun stateIsInitiallyLoading() = runTest {
        assertEquals(UiState.Loading, viewModel.uiState.value)
    }

    @Test
    fun stateIsSuccessWhenDataReceived() = runTest {
        val data = listOf(Category("test1"), Category("test2"), Category("test3"))
        // Create an empty collector for the StateFlow
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect {} }
        categoriesRepository.setCategories(Resource.Success(data))
        assertEquals(UiState.Success(data), viewModel.uiState.value)
    }

    @Test
    fun stateIsErrorWhenDataNotReceived() = runTest {
        // Create an empty collector for the StateFlow
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect {} }
        categoriesRepository.setCategories(Resource.Error(ErrorType.Unknown))
        assertTrue { viewModel.uiState.value is UiState.Error }
    }

}