package com.alexmprog.thecocktails.core.network.mock

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

internal object MockDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse =
        when (request.method) {
            "GET" -> when (request.path) {
                "/list.php?c=list" -> ok("categories".json())
                "/list.php?i=list" -> ok("ingredients".json())
                "/list.php?g=list" -> ok("glasses".json())
                "/filter.php?${request.requestUrl?.query}" -> ok("cocktails".json())
                "/lookup.php?${request.requestUrl?.query}" -> ok("cocktail-details".json())
                else -> badRequest()
            }
            else -> badRequest()
        }

    private fun ok(body: String? = null) = response(200, body)

    private fun badRequest() = response(400)

    private fun response(code: Int = 200, body: String? = null) = MockResponse().apply {
        setResponseCode(code)
        body?.let { setBody(body) }
    }

    private fun String.json(): String =
        MockDispatcher::class.java.getResource("/$this.json")!!.readText()

}