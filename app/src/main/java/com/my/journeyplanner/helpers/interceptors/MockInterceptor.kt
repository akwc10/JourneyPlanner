package com.my.journeyplanner.helpers.interceptors

import okhttp3.*

class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(chain.request())
            .newBuilder()
            .code(SUCCESS_CODE)
            .protocol(Protocol.HTTP_2)
            .message(RESPONSE_STRING)
            .body(
                ResponseBody.create(
                    MediaType.parse(APPLICATION_JSON),
                    RESPONSE_STRING.toByteArray()
                )
            )
            .addHeader(CONTENT_TYPE, APPLICATION_JSON)
            .build()

    companion object {
        const val SUCCESS_CODE = 200
        const val RESPONSE_STRING = "[{\"mock_key\": \"mock value\"}]"
        const val APPLICATION_JSON = "application/json"
        const val CONTENT_TYPE = "content-type"
    }
}