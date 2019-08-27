package com.my.journeyplanner.presenters

import android.util.Log
import com.my.journeyplanner.helpers.JourneyPlannerApiService
import com.my.journeyplanner.helpers.countOccurrences
import com.my.journeyplanner.models.JourneyPlanner
import com.my.journeyplanner.views.main.MainContract
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private var call: Call<JourneyPlanner.ItineraryResult>? = null

    override fun onChangeTimeClicked() {

    }

    override fun onEditPreferencesClicked() {

    }

    override fun onPlanMyJourneyClicked() {
        call = JourneyPlannerApiService.createApiService().getJourneyResults(
            view.getFromLocation().text.toString(),
            view.getToLocation().text.toString()
        )

        call!!.enqueue(object : Callback<JourneyPlanner.ItineraryResult> {
            override fun onFailure(
                call: Call<JourneyPlanner.ItineraryResult>,
                throwable: Throwable
            ) {
                if (throwable is IOException) {
                    Log.d(TAG, "*** Network failure ***")
                } else {
                    Log.d(TAG, "*** Conversion issue ***")
                }
            }

            override fun onResponse(
                call: Call<JourneyPlanner.ItineraryResult>,
                response: Response<JourneyPlanner.ItineraryResult>
            ) {
                if (response.isSuccessful) {
                    Log.d(TAG, "*** ItineraryResult: ${response.body()} ***")
//                TODO("Display data in new activity")
                } else when (response.code()) {
                    300 -> {
                        val jsonString = try {
                            response.errorBody()?.source()?.peek()?.readUtf8()
                        } catch (e: IOException) {
                            Log.d(TAG, "${e.message}\n Unable to peek errorBody")
                            ""
                        }

                        when {
                            jsonString?.countOccurrences(NOT_IDENTIFIED)!! > 1 -> {
//                                TODO("How to de-dupe?")
                                val converter: Converter<ResponseBody, JourneyPlanner.NotIdentifiedResult> =
                                    JourneyPlannerApiService.retrofit.responseBodyConverter(
                                        JourneyPlanner.NotIdentifiedResult::class.java,
                                        arrayOfNulls<Annotation>(0)
                                    )

                                try {
                                    val responseErrorBody = response.errorBody()

                                    if (responseErrorBody != null) {
                                        val notIdentifiedResult =
                                            converter.convert(responseErrorBody)
                                        Log.d(
                                            TAG,
                                            "*** notIdentifiedResult: $notIdentifiedResult ***"
                                        )
                                    }
                                } catch (e: IOException) {
                                    Log.d(TAG, "Unable to convert errorBody() to JSON")
                                }
//                                TODO("Prompt no search results and to update search criteria")
                            }
                            jsonString.contains(DISAMBIGUATION_OPTIONS) -> {
                                val converter: Converter<ResponseBody, JourneyPlanner.DisambiguationResult> =
                                    JourneyPlannerApiService.retrofit.responseBodyConverter(
                                        JourneyPlanner.DisambiguationResult::class.java,
                                        arrayOfNulls<Annotation>(0)
                                    )

                                try {
                                    val responseErrorBody = response.errorBody()

                                    if (responseErrorBody != null) {
                                        val disambiguationResult =
                                            converter.convert(responseErrorBody)
                                        Log.d(
                                            TAG,
                                            "*** disambiguationResult: $disambiguationResult ***"
                                        )
                                    }
                                } catch (e: IOException) {
                                    Log.d(TAG, "Unable to convert errorBody() to JSON")
                                }
//                                TODO("Display options in new activity with selectable ListView for disambiguation")
                            }
                            else -> Log.d(TAG, "*** Response 300 No Match ***")
                        }
                    }
                    else -> Log.d(TAG, "*** Response code: ${response.code()} ***")
                }
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
        const val NOT_IDENTIFIED = "notidentified"
        const val DISAMBIGUATION_OPTIONS = "disambiguationOptions"
    }
}