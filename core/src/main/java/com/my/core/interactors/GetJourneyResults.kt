package com.my.core.interactors

import com.my.core.data.JourneyPlannerRepository

class GetJourneyResults(private val journeyPlannerRepository: JourneyPlannerRepository) {
    operator fun invoke(fromLocation: String, toLocation: String) =
        journeyPlannerRepository.getJourneyResults(fromLocation, toLocation)
}