package com.alexmprog.thecocktails.core.common

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val commonDispatcher: CommonDispatchers)

enum class CommonDispatchers {
    Default,
    IO,
}