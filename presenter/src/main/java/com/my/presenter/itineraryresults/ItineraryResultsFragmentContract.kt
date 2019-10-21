package com.my.presenter.itineraryresults

import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey
import com.my.presenter.base.BaseContract

class ItineraryResultsFragmentContract {
    interface View : BaseContract.View {
        fun updateJourneys(journeys: List<Journey>)
    }

    interface Presenter : BaseContract.Presenter<View>
}