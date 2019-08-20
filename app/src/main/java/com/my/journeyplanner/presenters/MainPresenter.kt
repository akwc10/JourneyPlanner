package com.my.journeyplanner.presenters

import android.util.Log
import com.my.journeyplanner.helpers.JourneyPlannerApiService
import com.my.journeyplanner.models.JourneyPlannerDisambiguationResult
import com.my.journeyplanner.views.main.MainContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private var call: Call<JourneyPlannerDisambiguationResult>? = null

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

/*            TODO("Default adapter treats response code 300 Multiple Options as unsuccessful and")
            TODO("puts response in the errorBody but is actually a valid response.")
            TODO("Implement custom adapter to parse the valid response in the errorBody")   */
        call!!.enqueue(object : Callback<JourneyPlannerDisambiguationResult> {
            override fun onFailure(call: Call<JourneyPlannerDisambiguationResult>, throwable: Throwable) {
                Log.e(TAG, "Other error: ${throwable.message}")
                Log.e(TAG, "Other error: ${throwable.stackTrace.forEach { println(it) }}")
            }

            override fun onResponse(
                call: Call<JourneyPlannerDisambiguationResult>,
                response: Response<JourneyPlannerDisambiguationResult>
            ) {
                Log.i(TAG, "response: $response")
                Log.i(TAG, "response.body(): ${response.body()}")
            }
        })
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