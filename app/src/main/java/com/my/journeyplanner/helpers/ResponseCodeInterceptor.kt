package com.my.journeyplanner.helpers

import okhttp3.Interceptor
import okhttp3.Response

class ResponseCodeInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        return if (response.code() == 300) {
            response.newBuilder().code(200).build()
        } else response
    }
}