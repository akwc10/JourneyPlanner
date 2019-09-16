package com.my.core.interactors

import com.my.core.data.IJourneyPlannerRepository

class GetJourneyResults(private val journeyPlannerRepository: IJourneyPlannerRepository) {
    operator fun invoke(fromLocation: String, toLocation: String) =
        journeyPlannerRepository.getJourneyResults(fromLocation, toLocation)
}