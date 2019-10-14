package com.my.presenter.results

import com.my.core.domain.JourneyPlannerResultDomainModel
import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey
import com.my.presenter.base.BaseContract

class ResultsContract {
    interface View : BaseContract.View {
        fun showMapActivity(mapItineraryResults: List<Any>)
        fun showSaveJourneyActivity(journeyDetails: List<Any>)
        fun showItineraryResultsLegsActivity(legs: List<Journey.Leg>)
        fun showItineraryResultsFragment(result: List<Journey>)
        fun showItineraryResultsLegsFragment(legs: List<Journey.Leg>)
        fun showDisambiguationResultsFragment(result: JourneyPlannerResultDomainModel.FromToDisambiguationOptions)
        fun showNoResultsFragment()
        fun showErrorFragment(error: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onAddFavouritesClicked(details: Any)
        fun onMapViewClicked(details: Any)
        fun onSaveJourneyClicked()
        fun onJourneyClicked(legs: List<Journey.Leg>)
        fun getJourneyResults(fromLocation: String, toLocation: String)
        fun cancelAsyncCall()
    }
}