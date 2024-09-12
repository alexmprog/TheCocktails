package com.alexmprog.thecocktails.core.domain

import com.alexmprog.thecocktails.core.data.repository.GlassesRepository
import com.alexmprog.thecocktails.core.model.Glass
import com.alexmprog.thecocktails.core.model.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGlassesUseCase @Inject constructor(
    private val glassesRepository: GlassesRepository
) {
    operator fun invoke(): Flow<Resource<List<Glass>>> = glassesRepository.getGlasses()
}