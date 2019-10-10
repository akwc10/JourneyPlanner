package com.my.presenter.main

import com.my.core.domain.ICancellable
import com.my.core.domain.ICustomCallback
import com.my.core.domain.IJourneyPlannerRepository
import com.my.core.domain.JourneyPlannerResultDomainModel
import mu.KotlinLogging
import java.io.IOException

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
            view.getFromLocation(),
            view.getToLocation(),
            object : ICustomCallback<JourneyPlannerResultDomainModel> {
                override fun onSuccess(result: JourneyPlannerResultDomainModel) {
                    view.showResult("")
                    when (result) {
                        is JourneyPlannerResultDomainModel.Itinerary -> {
                            view.showItineraryResultActivity(result)
                        }
                        is JourneyPlannerResultDomainModel.FromToDisambiguationOptions -> {
//                            TODO("No match. Please update search criteria")
                            view.showDisambiguationResultActivity(result)
                        }
                    }
                }

                override fun onError(t: Throwable) {
                    view.showResult(if (t is IOException) "Network failure" else "Conversion failure")
                }
            })
    }

    override fun onMyJourneysClicked() {

    }

    override fun cancelAsyncCall() {
        cancellable?.cancel()
    }
}