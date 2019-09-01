package com.my.journeyplanner.usecases

import com.my.journeyplanner.models.JourneyPlanner
import retrofit2.Call

interface IGetJourneyResults {
    fun getJourneyResults(
        fromLocation: String,
        toLocation: String
    ): Call<JourneyPlanner.ItineraryResult>
}