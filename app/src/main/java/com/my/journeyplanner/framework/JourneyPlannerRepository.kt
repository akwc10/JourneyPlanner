package com.my.journeyplanner.framework

import android.util.Log
import com.my.core.data.IJourneyPlannerRepository
import com.my.core.domain.JourneyPlannerResult
import com.my.journeyplanner.helpers.JourneyPlannerApiService
import com.my.journeyplanner.presenters.main.MainPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class JourneyPlannerRepository(private val mainPresenter: MainPresenter) :
    IJourneyPlannerRepository {
    override fun getJourneyResults(
        fromLocation: String,
        toLocation: String
    ): Call<JourneyPlannerResult> =
        JourneyPlannerApiService.createApiService().getJourneyResults(fromLocation, toLocation)

    override fun enqueue(call: Call<JourneyPlannerResult>) {
        call.enqueue(object : Callback<JourneyPlannerResult> {

            override fun onFailure(
                call: Call<JourneyPlannerResult>,
                throwable: Throwable
            ) {
                Log.e(
                    TAG,
                    if (throwable is IOException) "Network failure" else "Conversion failure",
                    throwable
                )
            }

            override fun onResponse(
                call: Call<JourneyPlannerResult>,
                response: Response<JourneyPlannerResult>
            ) {
                if (response.isSuccessful) {
                    val responseBodyString = response.body().toString()

                    Log.d(TAG, "raw(): ${response.raw()}")
                    Log.d(TAG, "body(): $responseBodyString")

                    mainPresenter.updateJourneyPlannerResult(response.body())
                    when {
                        responseBodyString.contains(ITINERARY_RESULT) ->
                            mainPresenter.updateJourneyPlannerResultType(JourneyPlannerResultType.ITINERARY_RESULT)
                        responseBodyString.contains(DISAMBIGUATION_OPTIONS) ->
                            mainPresenter.updateJourneyPlannerResultType(JourneyPlannerResultType.DISAMBIGUATION_RESULT)
                        else -> throw IllegalStateException()
                    }
                } else {
                    Log.d(TAG, "responseCode: ${response.code()}")
                }

            }
        })
    }

    override fun cancelAsyncCall(call: Call<JourneyPlannerResult>?) {
        call?.cancel()
    }

    companion object {
        val TAG = this::class.java.simpleName as String
    }
}