package com.alexmprog.thecocktails.core.domain.repository

import com.alexmprog.thecocktails.core.common.model.Resource
import com.alexmprog.thecocktails.core.domain.model.Glass
import kotlinx.coroutines.flow.Flow

interface GlassesRepository {

    fun getGlasses(): Flow<Resource<List<Glass>>>
}
