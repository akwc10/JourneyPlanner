package com.my.core.interactors

import com.my.core.data.IJourneyPlannerRepository
import com.my.core.domain.JourneyPlannerResult
import retrofit2.Call

class Enqueue(private val journeyPlannerRepository: IJourneyPlannerRepository) {
    operator fun invoke(call: Call<JourneyPlannerResult>) =
        journeyPlannerRepository.enqueue(call)
}