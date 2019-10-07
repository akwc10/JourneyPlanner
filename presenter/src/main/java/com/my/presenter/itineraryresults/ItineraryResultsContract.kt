package com.my.presenter.itineraryresults

import com.my.presenter.base.BaseContract

class ItineraryResultsContract {
    interface View : BaseContract.View {
        fun updateItineraryResultsListView(itineraryResults: List<Any>)
        fun showDetailedActivity(detailedItineraryResults: List<Any>)
        fun showMapActivity(mapItineraryResults: List<Any>)
        fun showSaveJourneyActivity(journeyDetails: List<Any>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        //        TODO("Web - Expands list with details or map. Android - open in new detailed or map activity")
        fun onAddFavouritesClicked(details: Any)
        fun onViewDetailedClicked(details: Any)
        fun onMapViewClicked(details: Any)
        fun onSaveJourneyClicked()
    }
}