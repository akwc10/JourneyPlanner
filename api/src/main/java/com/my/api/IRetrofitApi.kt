package com.my.api

import com.my.core.domain.JourneyPlannerResultDomainModel
import retrofit2.Call

interface IRetrofitApi {
    fun getJourneyResults(
        fromLocation: String,
        toLocation: String
    ): Call<JourneyPlannerResult>

    fun enqueue(call: Call<JourneyPlannerResult>)

    fun cancelAsyncCall(call: Call<JourneyPlannerResult>?)

    fun getJourneyPlannerDomainModel(): JourneyPlannerResultDomainModel?
}