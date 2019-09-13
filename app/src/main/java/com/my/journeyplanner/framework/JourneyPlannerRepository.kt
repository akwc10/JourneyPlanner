package com.my.journeyplanner.framework

import android.util.Log
import com.my.core.data.IJourneyPlannerRepository
import com.my.core.domain.JourneyPlannerResult
import com.my.journeyplanner.helpers.JourneyPlannerApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class JourneyPlannerRepository : IJourneyPlannerRepository {
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
                    Log.d(TAG, "raw(): ${response.raw()}")
                    Log.d(TAG, "body(): ${response.body()}")
//                TODO("Display data in new activity")
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