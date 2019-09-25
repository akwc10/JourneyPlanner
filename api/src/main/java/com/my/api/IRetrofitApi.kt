package com.my.api

import retrofit2.Call

interface IRetrofitApi {
    fun getJourneyResults(
        fromLocation: String,
        toLocation: String
    ): Call<JourneyPlannerResult>

    fun enqueue(call: Call<JourneyPlannerResult>)

    fun cancelAsyncCall(call: Call<JourneyPlannerResult>?)
}