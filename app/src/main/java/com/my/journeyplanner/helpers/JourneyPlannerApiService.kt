package com.my.journeyplanner.helpers

import com.my.journeyplanner.TFL_API_BASE_URL
import com.my.journeyplanner.models.JourneyPlannerDisambiguationResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JourneyPlannerApiService {
    @GET("Journey/JourneyResults/{fromLocation}/to/{toLocation}?")
    fun getJourneyResults(
        @Path("fromLocation") fromLocation: String,
        @Path("toLocation") toLocation: String,
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String
    ): Call<JourneyPlannerDisambiguationResult>

    companion object {
        fun apiService(): JourneyPlannerApiService = Retrofit.Builder()
            .baseUrl(TFL_API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(JourneyPlannerApiService::class.java)
    }
}