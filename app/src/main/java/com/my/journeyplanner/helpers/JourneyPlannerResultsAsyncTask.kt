package com.my.journeyplanner.helpers

import android.os.AsyncTask
import android.util.Log
import android.widget.EditText
import java.lang.ref.WeakReference

internal class JourneyPlannerResultsAsyncTask(editTextFromLocation: EditText, editTextToLocation: EditText) :
    AsyncTask<Void, Void, String>() {

    private val editTextFromLocationReference: WeakReference<EditText> by lazy { WeakReference(editTextFromLocation) }
    private val editTextToLocationReference: WeakReference<EditText> by lazy { WeakReference(editTextToLocation) }

    override fun doInBackground(vararg params: Void?): String {
        val response = JourneyPlannerApiService.apiService().getJourneyResults(
            editTextFromLocationReference.get()?.text.toString(),
            editTextToLocationReference.get()?.text.toString(),
            TFL_API_APP_ID,
            TFL_API_APP_KEY
        ).execute()

//        TODO("Empty response message. Fix JourneyPlannerDisambiguationResult with $type ref in static annotation")
        Log.i(TAG, response.toString())
        Log.i(TAG, "*** response.raw: ${response.raw()}")
        Log.i(TAG, "*** response.code: ${response.code()}")

        if (response.isSuccessful) {
            Log.i(TAG, "Response successful")
            return response.body()?.fromLocationDisambiguation.toString()
        }
        Log.i(TAG, "Response unsuccessful")
        return "Response unsuccessful"
    }

    companion object {
        val TAG = this::class.java.simpleName as String
    }
}