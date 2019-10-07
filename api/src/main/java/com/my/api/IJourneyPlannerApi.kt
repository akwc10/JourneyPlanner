package com.my.api

import com.my.core.domain.ICancellable
import com.my.core.domain.ICustomCallback
import com.my.core.domain.JourneyPlannerResultDomainModel

interface IJourneyPlannerApi {
    fun getJourneyResults(
        fromLocation: String,
        toLocation: String,
        callback: ICustomCallback<JourneyPlannerResultDomainModel>
    ): ICancellable
}