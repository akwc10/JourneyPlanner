package com.my.core.interactors

import com.my.core.data.JourneyPlannerRepository

class BuildMockRetrofit(private val journeyPlannerRepository: JourneyPlannerRepository) {
    operator fun invoke() = journeyPlannerRepository.buildMockRetrofit()
}