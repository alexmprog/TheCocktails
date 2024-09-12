package com.alexmprog.thecocktails.core.domain

import com.alexmprog.thecocktails.core.data.repository.CategoriesRepository
import com.alexmprog.thecocktails.core.model.Category
import com.alexmprog.thecocktails.core.model.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository
) {
    operator fun invoke(): Flow<Resource<List<Category>>> = categoriesRepository.getCategories()
}