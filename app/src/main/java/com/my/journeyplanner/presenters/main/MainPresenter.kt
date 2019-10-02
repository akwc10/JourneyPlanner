package com.my.journeyplanner.presenters.main

import com.my.api.ICustomCallback
import com.my.core.domain.JourneyPlannerResultDomainModel
import com.my.journeyplanner.views.main.MainContract
import com.my.repository.ICancellable
import com.my.repository.JourneyPlannerRepository
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class MainPresenter(
    private val view: MainContract.View,
    private val journeyPlannerRepository: JourneyPlannerRepository
) :
    MainContract.Presenter {
    private var cancellable: ICancellable? = null

    override fun onChangeTimeClicked() {

    }

    override fun onEditPreferencesClicked() {

    }

    override fun onPlanMyJourneyClicked() {
        cancellable = journeyPlannerRepository.getJourneyResults(
            view.getFromLocation().text.toString(),
            view.getToLocation().text.toString(),
            object : ICustomCallback<JourneyPlannerResultDomainModel> {
                override fun onSuccess(result: JourneyPlannerResultDomainModel) {
                    view.showResult(result.toString())
                }

                override fun onError(t: Throwable) {
                    logger.error(t.message, t)
                }
            })
    }

    override fun onMyJourneysClicked() {

    }

    override fun cancelAsyncCall() {
        cancellable?.cancel()
    }
}