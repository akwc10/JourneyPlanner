package com.my.journeyplanner.features.searchquery.presenters

import com.my.journeyplanner.features.searchquery.domain.JourneyPlannerResult
import com.my.journeyplanner.features.searchquery.framework.Interactors
import com.my.journeyplanner.features.searchquery.views.main.MainContract
import retrofit2.Call

class MainPresenter(private val view: MainContract.View, private val interactors: Interactors) :
    MainContract.Presenter {
    private var call: Call<JourneyPlannerResult>? = null

    override fun onChangeTimeClicked() {

    }

    override fun onEditPreferencesClicked() {

    }

    override fun onPlanMyJourneyClicked() {
        call = interactors.getJourneyResults(
            view.getFromLocation().text.toString(),
            view.getToLocation().text.toString()
        )
        interactors.enqueue(call!!)
    }

    override fun onMyJourneysClicked() {

    }

    override fun cancelAsyncCall() {
        interactors.cancelAsyncCall(call)
    }
}