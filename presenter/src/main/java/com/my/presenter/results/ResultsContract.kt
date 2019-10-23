package com.my.presenter.results

import com.my.core.domain.JourneyPlannerResultDomainModel
import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey
import com.my.presenter.base.BaseContract

class ResultsContract {
    interface View : BaseContract.View {
        fun showMapActivity(mapItineraryResults: List<Any>)
        fun showSaveJourneyActivity(journeyDetails: List<Any>)
        fun showDisambiguationResultsFragment(disambiguationOptions: JourneyPlannerResultDomainModel.FromToDisambiguationOptions)
        fun showNoResultsFragment()
        fun showErrorSnackbar(error: String)
        fun showItineraryResultsFragment(journeys: List<Journey>)
        fun showDetailedJourneyActivity(journey: Journey)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onAddFavouritesClicked(details: Any)
        fun onMapViewClicked(details: Any)
        fun onSaveJourneyClicked()
        fun getJourneyResults(fromLocation: String, toLocation: String)
        fun cancelAsyncCall()
    }
}