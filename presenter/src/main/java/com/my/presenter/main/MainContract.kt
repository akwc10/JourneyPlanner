package com.my.presenter.main

class MainContract {
    interface View : BaseContract.View {
        fun getFromLocation(): IEditText
        fun getToLocation(): IEditText
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