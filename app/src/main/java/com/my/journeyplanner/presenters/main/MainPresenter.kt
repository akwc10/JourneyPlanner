package com.my.journeyplanner.presenters.main

import com.my.api.JourneyPlannerResult
import com.my.core.domain.JourneyPlannerResultDomainModel
import com.my.journeyplanner.views.main.MainContract
import com.my.repository.JourneyPlannerRepository
import retrofit2.Call

class MainPresenter(
    private val view: MainContract.View,
    private val journeyPlannerRepository: JourneyPlannerRepository
) :
    MainContract.Presenter {
    private var call: Call<JourneyPlannerResult>? = null

    override fun onChangeTimeClicked() {

    }

    override fun onEditPreferencesClicked() {

    }

    override fun onPlanMyJourneyClicked() {
        val journeyPlannerResultDomainModels = mutableListOf<JourneyPlannerResultDomainModel?>()
        call = journeyPlannerRepository.getJourneyResults(
            view.getFromLocation().text.toString(),
            view.getToLocation().text.toString()
        )
        journeyPlannerRepository.enqueue(call!!, journeyPlannerResultDomainModels)

        view.showResult(if (journeyPlannerResultDomainModels.isNotEmpty()) journeyPlannerResultDomainModels[0].toString() else "")
    }

    override fun onMyJourneysClicked() {

    }

    override fun cancelAsyncCall() {
        journeyPlannerRepository.cancelAsyncCall(call)
    }
}