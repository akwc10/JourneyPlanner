package com.my.journeyplanner.presenters.main

import com.my.api.JourneyPlannerResult
import com.my.api.RetrofitApi
import com.my.journeyplanner.views.main.MainContract
import retrofit2.Call

class MainPresenter(private val view: MainContract.View, private val retrofitApi: RetrofitApi) :
    MainContract.Presenter {
    private var call: Call<JourneyPlannerResult>? = null

    override fun onChangeTimeClicked() {

    }

    override fun onEditPreferencesClicked() {

    }

    override fun onPlanMyJourneyClicked() {
        call = retrofitApi.getJourneyResults(
            view.getFromLocation().text.toString(),
            view.getToLocation().text.toString()
        )
        retrofitApi.enqueue(call!!)

        view.showResult(retrofitApi.journeyPlannerResultDomainModel.toString())
    }

    override fun onMyJourneysClicked() {

    }

    override fun cancelAsyncCall() {
        retrofitApi.cancelAsyncCall(call)
    }
}