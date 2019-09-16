package com.my.journeyplanner.features.searchquery.interactors

import com.my.journeyplanner.features.searchquery.data.IJourneyPlannerRepository
import com.my.journeyplanner.features.searchquery.domain.JourneyPlannerResult
import retrofit2.Call

class Enqueue(private val journeyPlannerRepository: IJourneyPlannerRepository) {
    operator fun invoke(call: Call<JourneyPlannerResult>) =
        journeyPlannerRepository.enqueue(call)
}