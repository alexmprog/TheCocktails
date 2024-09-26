package com.alexmprog.thecocktails.feature.glasses.list

import com.alexmprog.thecocktails.core.domain.GetGlassesUseCase
import com.alexmprog.thecocktails.core.model.ErrorType
import com.alexmprog.thecocktails.core.model.Glass
import com.alexmprog.thecocktails.core.model.Resource
import com.alexmprog.thecocktails.core.testing.repository.TestGlassesRepository
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

class GlassesListViewModelTest {

    @get:Rule
    val mainDispatcherRule = TestDispatcherRule()

    private val glassesRepository = TestGlassesRepository()

    private lateinit var viewModel: GlassesListViewModel

    @Before
    fun setup() {
        viewModel = GlassesListViewModel(GetGlassesUseCase(glassesRepository))
    }

    @Test
    fun stateIsInitiallyLoading() = runTest {
        assertEquals(UiState.Loading, viewModel.uiState.value)
    }

    @Test
    fun stateIsSuccessWhenDataReceived() = runTest {
        val data = listOf(Glass("test1"), Glass("test2"), Glass("test3"))
        // Create an empty collector for the StateFlow
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect {} }
        glassesRepository.setGlasses(Resource.Success(data))
        assertEquals(UiState.Success(data), viewModel.uiState.value)
    }

    @Test
    fun stateIsErrorWhenDataNotReceived() = runTest {
        // Create an empty collector for the StateFlow
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect {} }
        glassesRepository.setGlasses(Resource.Error(ErrorType.Unknown))
        assertTrue { viewModel.uiState.value is UiState.Error }
    }

}