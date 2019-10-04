package com.my.presenter.main

import com.my.presenter.base.BaseContract

class MainContract {
    interface View : BaseContract.View {
        fun getFromLocation(): String
        fun getToLocation(): String
        fun showItineraryResultActivity()
        fun showResult(result: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onChangeTimeClicked()
        fun onEditPreferencesClicked()
        fun onPlanMyJourneyClicked()
        fun onMyJourneysClicked()
        fun cancelAsyncCall()
    }
}