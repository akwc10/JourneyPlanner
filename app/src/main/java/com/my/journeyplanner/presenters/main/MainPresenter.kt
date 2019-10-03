package com.my.journeyplanner.presenters.main

import android.widget.EditText
import com.my.core.domain.ICancellable
import com.my.core.domain.ICustomCallback
import com.my.core.domain.IJourneyPlannerRepository
import com.my.core.domain.JourneyPlannerResultDomainModel
import com.my.presenter.main.MainContract
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class MainPresenter(
    private val view: MainContract.View,
    private val journeyPlannerRepository: IJourneyPlannerRepository
) : MainContract.Presenter {
    private var cancellable: ICancellable? = null

    override fun onChangeTimeClicked() {

    }

    override fun onEditPreferencesClicked() {

    }

    override fun onPlanMyJourneyClicked() {
        cancellable = journeyPlannerRepository.getJourneyResults(
            view.getFromLocation<EditText>().text.toString(),
            view.getToLocation<EditText>().text.toString(),
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