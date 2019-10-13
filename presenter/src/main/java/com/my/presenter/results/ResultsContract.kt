package com.my.presenter.results

import com.my.core.domain.JourneyPlannerResultDomainModel
import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey
import com.my.presenter.base.BaseContract

class ResultsContract {
    interface View : BaseContract.View {
        fun updateJourneys(journeys: List<Journey>)
        fun showDetailedActivity(detailedItineraryResults: List<Any>)
        fun showMapActivity(mapItineraryResults: List<Any>)
        fun showSaveJourneyActivity(journeyDetails: List<Any>)
        fun showResults()
        fun showItineraryResultsLegsActivity(legs: List<Journey.Leg>)
        fun showItineraryResultsFragment(result: List<Journey>)
        fun showDisambiguationResultsFragment(result: JourneyPlannerResultDomainModel.FromToDisambiguationOptions)
        fun showNoResultsFragment()
        fun showErrorFragment(error: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        //        TODO("Web - Expands list with details or map. Android - open in new detailed or map activity")
        fun onAddFavouritesClicked(details: Any)
        fun onViewDetailedClicked(details: Any)
        fun onMapViewClicked(details: Any)
        fun onSaveJourneyClicked()
        fun onJourneyClicked(legs: List<Journey.Leg>)
        fun getJourneyResults(fromLocation: String, toLocation: String)
        fun cancelAsyncCall()
    }
}