package com.my.journeyplanner.presenters.itineraryresults

import com.my.journeyplanner.views.itineraryresults.ItineraryResultsContract

class ItineraryResultsPresenter(
    private val view: ItineraryResultsContract.View,
    private val itineraryResults: List<Any>
) : ItineraryResultsContract.Presenter {
    override fun onAddFavouritesClicked(details: Any) {

    }

    override fun onViewDetailedClicked(details: Any) {

    }

    override fun onMapViewClicked(details: Any) {

    }

    override fun onSaveJourneyClicked() {

    }
}