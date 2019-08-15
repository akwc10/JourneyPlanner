package com.my.journeyplanner.helpers

import android.os.AsyncTask
import android.util.Log

internal class JourneyPlannerResultsAsyncTask : AsyncTask<String, Void, String>() {
    override fun doInBackground(vararg params: String?): String {
        val response = JourneyPlannerApiService.createApiService.getJourneyResults(
            params[0] ?: "",
            params[1] ?: ""
        ).execute()

        if (response.isSuccessful) {
            return "Response successful"
        }

//        TODO("Remove")
        Log.i(TAG, "response: $response")
        response.errorBody()?.apply {
            Log.i(TAG, "errorBody().source(): ${source()}")
            Log.i(TAG, "errorBody().charStream().readText(): ${charStream().readText()}")
        }

        return "Response unsuccessful"
    }

    companion object {
        val TAG = this::class.java.simpleName as String
    }
}