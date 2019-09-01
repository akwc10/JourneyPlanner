package com.my.journeyplanner.usecases

import com.my.journeyplanner.helpers.JourneyPlannerApiService
import retrofit2.Retrofit

class BuildMockRetrofit : IBuildMockRetrofit {
    operator fun invoke(): Retrofit = buildMockRetrofit()

    override fun buildMockRetrofit(): Retrofit = JourneyPlannerApiService.mockRetrofit
}