package com.alexmprog.thecocktails.core.testing.repository

import com.alexmprog.thecocktails.core.data.repository.GlassesRepository
import com.alexmprog.thecocktails.core.model.Glass
import com.alexmprog.thecocktails.core.model.Resource
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestGlassesRepository : GlassesRepository {

    private val glassesFlow: MutableSharedFlow<Resource<List<Glass>>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getGlasses(): Flow<Resource<List<Glass>>> = glassesFlow

    fun setGlasses(resource: Resource<List<Glass>>) {
        glassesFlow.tryEmit(resource)
    }
}