package com.my.core.interactors

import com.my.core.data.JourneyPlannerRepository
import com.my.core.domain.JourneyPlanner
import retrofit2.Call

class Enqueue(private val journeyPlannerRepository: JourneyPlannerRepository) {
    operator fun invoke(call: Call<JourneyPlanner.ItineraryResult>) =
        journeyPlannerRepository.enqueue(call)
}