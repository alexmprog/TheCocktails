package com.alexmprog.thecocktails.core.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

internal fun <RESULT, REQUEST> networkBoundedFlow(
    local: Flow<RESULT>,
    remote: suspend () -> REQUEST,
    save: suspend (response: REQUEST) -> Unit,
    log: (e: Throwable) -> Unit = { Log.e("networkBoundedFlow", "error: ${it.message}") }
): Flow<RESULT> = flow {
    runCatching {
        local.first()?.let { emit(it) }
        remote()?.let { save(it) }
    }.onFailure {
        log(it)
    }
    emitAll(local.filter { it != null })
}