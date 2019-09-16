package com.my.journeyplanner.features.searchquery.interactors

import com.my.journeyplanner.features.searchquery.data.IJourneyPlannerRepository
import com.my.journeyplanner.features.searchquery.domain.JourneyPlannerResult
import retrofit2.Call

class CancelAsyncCall(private val journeyPlannerRepository: IJourneyPlannerRepository) {
    operator fun invoke(call: Call<JourneyPlannerResult>?) =
        journeyPlannerRepository.cancelAsyncCall(call)
}