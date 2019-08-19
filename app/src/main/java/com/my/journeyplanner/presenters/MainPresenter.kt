package com.my.journeyplanner.presenters

import com.my.journeyplanner.helpers.JourneyPlannerApiService
import com.my.journeyplanner.helpers.retrofitcustomadapter.CustomCall
import com.my.journeyplanner.models.JourneyPlannerDisambiguationResult
import com.my.journeyplanner.views.main.MainContract

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private var call: CustomCall<JourneyPlannerDisambiguationResult>? = null

    override fun onChangeTimeClicked() {

    }

    override fun onEditPreferencesClicked() {

    }

    override fun onPlanMyJourneyClicked() {
        val apiService = JourneyPlannerApiService.createApiService()

        call = apiService.getJourneyResults(
            view.getFromLocation().text.toString(),
            view.getToLocation().text.toString()
        )

        call!!.enqueue { journeyPlannerDisambiguationResult, throwable ->
            println("test: $journeyPlannerDisambiguationResult")
        }

/*            TODO("Default adapter treats response code 300 Multiple Options as unsuccessful and")
            TODO("puts response in the errorBody but is actually a valid response.")
            TODO("Implement custom adapter to parse the valid response in the errorBody")   */
//        call!!.enqueue(object : Callback<JourneyPlannerDisambiguationResult> {
//            override fun onFailure(call: Call<JourneyPlannerDisambiguationResult>, throwable: Throwable) {
//                Log.e(TAG, "Other error: ${throwable.message}")
//                Log.e(TAG, "Other error: ${throwable.stackTrace.forEach { println(it) }}")
//            }
//
//            override fun onResponse(
//                call: Call<JourneyPlannerDisambiguationResult>,
//                response: Response<JourneyPlannerDisambiguationResult>
//            ) {
//                Log.i(TAG, "response: $response")
//                response.errorBody()?.apply {
//                    Log.i(TAG, "errorBody().source(): ${source()}")
//                    Log.i(TAG, "errorBody().charStream().readText(): ${charStream().readText()}")
//                }
//            }
//        })
    }

    override fun onMyJourneysClicked() {

    }

    override fun cancelAsyncCall() {
        call?.cancel()
    }

    companion object {
        val TAG = this::class.java.simpleName as String
    }
}