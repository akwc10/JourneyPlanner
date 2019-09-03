package com.my.core.data

import com.my.core.domain.JourneyPlanner
import retrofit2.Call

class JourneyPlannerRepository(private val journeyPlannerDataSource: IJourneyPlannerDataSource) {
    fun getJourneyResults(
        fromLocation: String,
        toLocation: String
    ): Call<JourneyPlanner.ItineraryResult> =
        journeyPlannerDataSource.getJourneyResults(fromLocation, toLocation)

    fun enqueue(call: Call<JourneyPlanner.ItineraryResult>) = journeyPlannerDataSource.enqueue(call)

    fun cancelAsyncCall(call: Call<JourneyPlanner.ItineraryResult>?) =
        journeyPlannerDataSource.cancelAsyncCall(call)

    fun buildMockRetrofit() = journeyPlannerDataSource.buildMockRetrofit()
}