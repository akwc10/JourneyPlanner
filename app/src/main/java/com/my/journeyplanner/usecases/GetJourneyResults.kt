package com.my.journeyplanner.usecases

import com.my.journeyplanner.helpers.JourneyPlannerApiService
import com.my.journeyplanner.models.JourneyPlanner
import retrofit2.Call

class GetJourneyResults :
    IGetJourneyResults {
    operator fun invoke(
        fromLocation: String,
        toLocation: String
    ): Call<JourneyPlanner.ItineraryResult> =
        getJourneyResults(fromLocation, toLocation)

    override fun getJourneyResults(fromLocation: String, toLocation: String):
            Call<JourneyPlanner.ItineraryResult> =
        JourneyPlannerApiService.createApiService().getJourneyResults(fromLocation, toLocation)
}