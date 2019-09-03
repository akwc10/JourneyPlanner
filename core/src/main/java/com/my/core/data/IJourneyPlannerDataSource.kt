package com.my.core.data

import com.my.core.domain.JourneyPlanner
import retrofit2.Call
import retrofit2.Retrofit

interface IJourneyPlannerDataSource {
    fun getJourneyResults(
        fromLocation: String,
        toLocation: String
    ): Call<JourneyPlanner.ItineraryResult>

    fun enqueue(call: Call<JourneyPlanner.ItineraryResult>)

    fun cancelAsyncCall(call: Call<JourneyPlanner.ItineraryResult>?)

    fun buildMockRetrofit(): Retrofit
}