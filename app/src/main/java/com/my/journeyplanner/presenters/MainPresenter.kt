package com.my.journeyplanner.presenters

import android.util.Log
import com.my.journeyplanner.apiService
import com.my.journeyplanner.models.JourneyPlannerDisambiguationResult
import com.my.journeyplanner.views.main.MainContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    override fun onChangTimeClicked() {

    }

    override fun onEditPreferencesClicked() {

    }

    override fun onPlanMyJourneyClicked(): Call<JourneyPlannerDisambiguationResult> {
        val call = apiService.getJourneyResults(
            view.getFromLocation().text.toString(),
            view.getToLocation().text.toString()
        )

        call.enqueue(object : Callback<JourneyPlannerDisambiguationResult> {
            override fun onFailure(call: Call<JourneyPlannerDisambiguationResult>, throwable: Throwable) {
                Log.e(TAG, "Other error: ${throwable.message}")
                Log.e(TAG, "Other error: ${throwable.stackTrace.forEach { println(it) }}")
            }

            override fun onResponse(
                call: Call<JourneyPlannerDisambiguationResult>,
                response: Response<JourneyPlannerDisambiguationResult>
            ) {
                Log.i(TAG, "response: $response")
                response.errorBody()?.apply {
                    Log.i(TAG, "errorBody().source(): ${source()}")
                    Log.i(TAG, "errorBody().charStream().readText(): ${charStream().readText()}")
                }
            }
        })
        return call
    }

    override fun onMyJourneysClicked() {

    }

    companion object {
        val TAG = this::class.java.simpleName as String
    }
}