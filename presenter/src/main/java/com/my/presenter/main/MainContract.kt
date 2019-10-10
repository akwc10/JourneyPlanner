package com.my.presenter.main

import com.my.core.domain.JourneyPlannerResultDomainModel
import com.my.presenter.base.BaseContract

class MainContract {
    interface View : BaseContract.View {
        fun getFromLocation(): String
        fun getToLocation(): String
        fun showItineraryResultActivity(journeyPlannerItineraryResultDomainModel: JourneyPlannerResultDomainModel.Itinerary)
        fun showResult(result: String)
        fun showDisambiguationResultActivity(journeyPlannerDisambiguationResultDomainModel: JourneyPlannerResultDomainModel.FromToDisambiguationOptions)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onChangeTimeClicked()
        fun onEditPreferencesClicked()
        fun onPlanMyJourneyClicked()
        fun onMyJourneysClicked()
        fun cancelAsyncCall()
    }
}