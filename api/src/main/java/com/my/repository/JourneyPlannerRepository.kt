package com.my.repository

import com.my.api.JourneyPlannerApi
import com.my.api.JourneyPlannerResult
import com.my.core.domain.JourneyPlannerResultDomainModel
import retrofit2.Call

class JourneyPlannerRepository : IJourneyPlannerRepository {
    private val journeyPlannerApi = JourneyPlannerApi()

    override fun getJourneyResults(
        fromLocation: String,
        toLocation: String
    ): Call<JourneyPlannerResult> = journeyPlannerApi.getJourneyResults(fromLocation, toLocation)

    override fun enqueue(
        call: Call<JourneyPlannerResult>,
        journeyPlannerResultDomainModels: MutableList<JourneyPlannerResultDomainModel?>
    ) {
        journeyPlannerApi.enqueue(call, journeyPlannerResultDomainModels)
    }

    override fun cancelAsyncCall(call: Call<JourneyPlannerResult>?) {

    }
}