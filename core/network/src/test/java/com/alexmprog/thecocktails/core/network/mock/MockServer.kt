package com.alexmprog.thecocktails.core.network.mock

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit

object MockServer {

    private val internal = MockWebServer().apply {
        dispatcher = MockDispatcher
        start()
    }

    val retrofit = Retrofit.Builder()
        .baseUrl(internal.url("/"))
        .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()))
        .build()

    fun <T> createService(service: Class<T>): T = retrofit.create(service)
}