package com.my.journeyplanner.helpers

import com.my.journeyplanner.models.JourneyPlanner
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
    ): Call<JourneyPlanner.ItineraryResult>

    companion object {
        private const val TFL_API_BASE_URL = "https://api.tfl.gov.uk/"
        private val client = OkHttpClient().newBuilder()
            .addInterceptor(ParametersInterceptor())
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(TFL_API_BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        fun createApiService(): JourneyPlannerApiService =
            retrofit.create(JourneyPlannerApiService::class.java)
    }
}