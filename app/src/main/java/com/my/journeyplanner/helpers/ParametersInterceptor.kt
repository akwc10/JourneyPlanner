package com.my.journeyplanner.helpers

import com.my.journeyplanner.TFL_API_APP_ID
import com.my.journeyplanner.TFL_API_APP_KEY
import okhttp3.Interceptor
import okhttp3.Response

class ParametersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url().newBuilder()
            .addQueryParameter("app_id", TFL_API_APP_ID)
            .addQueryParameter("app_key", TFL_API_APP_KEY)
            .build()
        val request = chain.request().newBuilder().url(url).build()

        return chain.proceed(request)
    }
}