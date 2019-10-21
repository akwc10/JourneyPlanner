package com.my.presenter.results

import com.my.core.domain.ICancellable
import com.my.core.domain.ICustomCallback
import com.my.core.domain.IJourneyPlannerRepository
import com.my.core.domain.JourneyPlannerResultDomainModel
import mu.KotlinLogging
import java.io.IOException

private val logger = KotlinLogging.logger {}

class ResultsPresenter(
    private val view: ResultsContract.View,
    private val journeyPlannerRepository: IJourneyPlannerRepository
) :
    ResultsContract.Presenter {
    private var cancellable: ICancellable? = null

    override fun onAddFavouritesClicked(details: Any) {

    }

    override fun onMapViewClicked(details: Any) {

    }

    override fun onSaveJourneyClicked() {

    }

    override fun getJourneyResults(fromLocation: String, toLocation: String) {
        cancellable = journeyPlannerRepository.getJourneyResults(
            fromLocation,
            toLocation,
            object : ICustomCallback<JourneyPlannerResultDomainModel> {
                override fun onSuccess(result: JourneyPlannerResultDomainModel) {
                    when (result) {
                        is JourneyPlannerResultDomainModel.Itinerary -> {
                            view.showItineraryResultsCardviewFragment(result.journeys)
                        }
                        is JourneyPlannerResultDomainModel.FromToDisambiguationOptions -> {
//                            TODO("No match")
                            view.showDisambiguationResultsFragment(result)
                        }
                    }
                }

                override fun onError(t: Throwable) {
                    view.showErrorSnackbar(if (t is IOException) "Network failure" else "Conversion failure")
                }
            })
    }

    override fun cancelAsyncCall() {
        cancellable?.cancel()
    }
}