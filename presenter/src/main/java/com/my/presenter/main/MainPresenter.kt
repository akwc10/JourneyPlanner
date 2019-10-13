package com.my.presenter.main

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {
    override fun onChangeTimeClicked() {

    }

    override fun onEditPreferencesClicked() {

    }

    override fun onPlanMyJourneyClicked() {
        view.showResultActivity()
    }

    override fun onMyJourneysClicked() {

    }

}