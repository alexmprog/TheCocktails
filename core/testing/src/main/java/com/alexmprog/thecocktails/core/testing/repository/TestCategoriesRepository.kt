package com.alexmprog.thecocktails.core.testing.repository

import com.alexmprog.thecocktails.core.domain.model.Category
import com.alexmprog.thecocktails.core.common.model.Resource
import com.alexmprog.thecocktails.core.domain.repository.CategoriesRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestCategoriesRepository : CategoriesRepository {

    private val categoriesFlow: MutableSharedFlow<Resource<List<Category>>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getCategories(): Flow<Resource<List<Category>>> = categoriesFlow

    fun setCategories(resource: Resource<List<Category>>) {
        categoriesFlow.tryEmit(resource)
    }
}

