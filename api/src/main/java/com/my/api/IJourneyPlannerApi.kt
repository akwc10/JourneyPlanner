package com.my.api

import com.my.core.domain.JourneyPlannerResultDomainModel
import retrofit2.Call

interface IJourneyPlannerApi {
    fun getJourneyResults(
        fromLocation: String,
        toLocation: String
    ): Call<JourneyPlannerResult>

    fun enqueue(
        call: Call<JourneyPlannerResult>,
        journeyPlannerResultDomainModels: MutableList<JourneyPlannerResultDomainModel?>
    )

    fun cancelAsyncCall(call: Call<JourneyPlannerResult>?)
}