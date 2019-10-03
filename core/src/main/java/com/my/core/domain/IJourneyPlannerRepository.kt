package com.my.core.domain

interface IJourneyPlannerRepository {
    fun getJourneyResults(
        fromLocation: String,
        toLocation: String,
        callback: ICustomCallback<JourneyPlannerResultDomainModel>
    ): ICancellable
}