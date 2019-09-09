package com.my.core.data

import com.my.core.domain.JourneyPlannerResult
import retrofit2.Call

interface IJourneyPlannerRepository {
    fun getJourneyResults(
        fromLocation: String,
        toLocation: String
    ): Call<JourneyPlannerResult.FromAndToDisambiguationOptions>

    fun enqueue(call: Call<JourneyPlannerResult.FromAndToDisambiguationOptions>)

    fun cancelAsyncCall(call: Call<JourneyPlannerResult.FromAndToDisambiguationOptions>?)
}