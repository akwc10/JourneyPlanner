package com.my.journeyplanner.features.searchquery.framework.helpers

import com.my.journeyplanner.features.searchquery.domain.JourneyPlannerResult
import com.my.journeyplanner.features.searchquery.framework.JourneyPlannerResultConverterFactory
import com.my.journeyplanner.features.searchquery.framework.helpers.interceptors.MultiOptionResponseCodeInterceptor
import com.my.journeyplanner.features.searchquery.framework.helpers.interceptors.ParametersInterceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface JourneyPlannerApiService {
    @GET("Journey/JourneyResults/{fromLocation}/to/{toLocation}?")
    fun getJourneyResults(
        @Path("fromLocation") fromLocation: String,
        @Path("toLocation") toLocation: String
    ): Call<JourneyPlannerResult>

    companion object {
        private const val TFL_API_BASE_URL = "https://api.tfl.gov.uk/"
        private val client = OkHttpClient().newBuilder()
            .addInterceptor(ParametersInterceptor())
            .addInterceptor(MultiOptionResponseCodeInterceptor())
            .build()

        fun createApiService(): JourneyPlannerApiService =
            Retrofit.Builder()
                .baseUrl(TFL_API_BASE_URL)
                .client(client)
                .addConverterFactory(JourneyPlannerResultConverterFactory())
                .addConverterFactory(MoshiConverterFactory.create())
                .build().create(JourneyPlannerApiService::class.java)
    }
}