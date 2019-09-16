package com.my.journeyplanner.features.searchquery.data

import com.my.journeyplanner.features.searchquery.domain.JourneyPlannerResult
import retrofit2.Call

interface IJourneyPlannerRepository {
    fun getJourneyResults(
        fromLocation: String,
        toLocation: String
    ): Call<JourneyPlannerResult>

    fun enqueue(call: Call<JourneyPlannerResult>)

    fun cancelAsyncCall(call: Call<JourneyPlannerResult>?)
}