package com.alexmprog.thecocktails.core.domain.usecase

import com.alexmprog.thecocktails.core.domain.model.Glass
import com.alexmprog.thecocktails.core.common.model.Resource
import com.alexmprog.thecocktails.core.domain.repository.GlassesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGlassesUseCase @Inject constructor(
    private val glassesRepository: GlassesRepository
) {
    operator fun invoke(): Flow<Resource<List<Glass>>> = glassesRepository.getGlasses()
}