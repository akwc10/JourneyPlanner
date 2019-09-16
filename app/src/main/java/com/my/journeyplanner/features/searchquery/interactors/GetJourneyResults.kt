package com.my.journeyplanner.features.searchquery.interactors

import com.my.journeyplanner.features.searchquery.data.IJourneyPlannerRepository

class GetJourneyResults(private val journeyPlannerRepository: IJourneyPlannerRepository) {
    operator fun invoke(fromLocation: String, toLocation: String) =
        journeyPlannerRepository.getJourneyResults(fromLocation, toLocation)
}