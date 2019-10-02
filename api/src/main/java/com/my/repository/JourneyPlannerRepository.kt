package com.my.repository

import com.my.api.ICustomCallback
import com.my.api.JourneyPlannerApi
import com.my.core.domain.JourneyPlannerResultDomainModel

class JourneyPlannerRepository : IJourneyPlannerRepository {
    private val journeyPlannerApi = JourneyPlannerApi()

    override fun getJourneyResults(
        fromLocation: String,
        toLocation: String,
        callback: ICustomCallback<JourneyPlannerResultDomainModel>
    ): ICancellable {
        val call = journeyPlannerApi.getJourneyResults(fromLocation, toLocation)
        journeyPlannerApi.enqueue(call, callback)
        return object : ICancellable {
            override fun cancel() {
                call.cancel()
            }
        }
    }
}