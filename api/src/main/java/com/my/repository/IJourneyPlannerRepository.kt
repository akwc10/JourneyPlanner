package com.my.repository

import com.my.api.ICustomCallback
import com.my.core.domain.JourneyPlannerResultDomainModel

interface IJourneyPlannerRepository {
    fun getJourneyResults(
        fromLocation: String,
        toLocation: String,
        callback: ICustomCallback<JourneyPlannerResultDomainModel>
    ): ICancellable
}