package com.my.journeyplanner.presenters.main

import com.my.core.domain.JourneyPlannerResult
import com.my.journeyplanner.framework.Interactors
import com.my.journeyplanner.framework.JourneyPlannerResultType
import com.my.journeyplanner.views.main.MainContract
import retrofit2.Call

class MainPresenter(private val view: MainContract.View, private val interactors: Interactors) :
    MainContract.Presenter {
    private var call: Call<JourneyPlannerResult>? = null
    private var journeyPlannerResult: JourneyPlannerResult? = null
    private var journeyPlannerResultType: JourneyPlannerResultType? = null

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

    override fun updateJourneyPlannerResult(journeyPlannerResult: JourneyPlannerResult?) {
        this.journeyPlannerResult = journeyPlannerResult
    }

    override fun getJourneyPlannerResult(): JourneyPlannerResult? = journeyPlannerResult

    override fun updateJourneyPlannerResultType(journeyPlannerResultType: JourneyPlannerResultType?) {
        this.journeyPlannerResultType = journeyPlannerResultType
    }

    override fun getJourneyPlannerResultType(): JourneyPlannerResultType? = journeyPlannerResultType
}