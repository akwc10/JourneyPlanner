package com.my.presenter.itineraryresults

import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey.Leg

class ItineraryResultsPresenter(private val view: ItineraryResultsContract.View) :
    ItineraryResultsContract.Presenter {
    override fun onAddFavouritesClicked(details: Any) {

    }

    override fun onViewDetailedClicked(details: Any) {

    }

    override fun onMapViewClicked(details: Any) {

    }

    override fun onSaveJourneyClicked() {

    }

    override fun onJourneyClicked(legs: List<Leg>) {
        view.showItineraryResultsLegsActivity(legs)
    }
}