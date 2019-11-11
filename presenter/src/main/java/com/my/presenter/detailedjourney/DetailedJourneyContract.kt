package com.my.presenter.detailedjourney

import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey
import com.my.presenter.base.BaseContract

class DetailedJourneyContract {
    interface View : BaseContract.View {
        fun updateDetailedJourney(journey: Journey)
    }

    interface Presenter : BaseContract.Presenter<View>
}