package com.my.presenter.itineraryresultslegs

import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey.Leg
import com.my.presenter.base.BaseContract

class ItineraryResultsLegsContract {
    interface View : BaseContract.View {
        fun updateLegs(legs: List<Leg>)
    }

    interface Presenter : BaseContract.Presenter<View>
}