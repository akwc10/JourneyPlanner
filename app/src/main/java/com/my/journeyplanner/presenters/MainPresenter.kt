package com.my.journeyplanner.presenters

import com.my.journeyplanner.helpers.JourneyPlannerResultsAsyncTask
import com.my.journeyplanner.views.main.MainContract

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    override fun onChangTimeClicked() {

    }

    override fun onEditPreferencesClicked() {

    }

    override fun onPlanMyJourneyClicked() {
        JourneyPlannerResultsAsyncTask().execute(
            view.getFromLocation().text.toString(),
            view.getToLocation().text.toString()
        )
    }

    override fun onMyJourneysClicked() {

    }
}