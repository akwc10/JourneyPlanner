package com.my.journeyplanner.framework

import android.util.Log
import com.my.core.data.IJourneyPlannerDataSource
import com.my.core.domain.JourneyPlanner
import com.my.journeyplanner.helpers.JourneyPlannerApiService
import com.my.journeyplanner.helpers.countOccurrences
import okhttp3.ResponseBody
import retrofit2.*
import java.io.IOException

class JourneyPlannerDataSource : IJourneyPlannerDataSource {
    override fun getJourneyResults(
        fromLocation: String,
        toLocation: String
    ): Call<JourneyPlanner.ItineraryResult> =
        JourneyPlannerApiService.createApiService().getJourneyResults(fromLocation, toLocation)

    override fun enqueue(call: Call<JourneyPlanner.ItineraryResult>) {
        call.enqueue(object : Callback<JourneyPlanner.ItineraryResult> {

            override fun onFailure(
                call: Call<JourneyPlanner.ItineraryResult>,
                throwable: Throwable
            ) {
                if (throwable is IOException) {
                    Log.d(TAG, "Network failure")
                } else {
                    Log.d(TAG, "Conversion failure")
                }
            }

            override fun onResponse(
                call: Call<JourneyPlanner.ItineraryResult>,
                response: Response<JourneyPlanner.ItineraryResult>
            ) {
                if (response.isSuccessful) {
                    Log.d(TAG, "itineraryResult: ${response.body()}")
//                TODO("Display data in new activity")
                } else when (val responseCode = response.code()) {
                    300 -> {
                        val jsonString = readJsonString(response)

                        when {
                            jsonString?.countOccurrences(NOT_IDENTIFIED)!! > 1 -> {
                                val converter: Converter<ResponseBody, JourneyPlanner.NotIdentifiedResult> =
                                    buildMockRetrofit().responseBodyConverter(
                                        JourneyPlanner.NotIdentifiedResult::class.java,
                                        arrayOfNulls<Annotation>(0)
                                    )
                                val notIdentifiedResult =
                                    convertResponseErrorBody(response, converter)
                                Log.d(TAG, "notIdentifiedResult: $notIdentifiedResult")
//                                TODO("Prompt no search results and to update search criteria")
                            }
                            jsonString.contains(DISAMBIGUATION_OPTIONS) -> {
                                val converter: Converter<ResponseBody, JourneyPlanner.DisambiguationResult> =
                                    buildMockRetrofit().responseBodyConverter(
                                        JourneyPlanner.DisambiguationResult::class.java,
                                        arrayOfNulls<Annotation>(0)
                                    )
                                val disambiguationResult =
                                    convertResponseErrorBody(response, converter)
                                Log.d(TAG, "disambiguationResult: $disambiguationResult")
//                                TODO("Display options in new activity with selectable ListView for disambiguation")
                            }
                            else -> Log.d(TAG, "responseCode: $responseCode. No Match")
                        }
                    }
                    else -> Log.d(TAG, "responseCode: $responseCode")
                }
            }
        })
    }

    private fun <T> readJsonString(response: Response<T>): String? {
        return try {
            response.errorBody()?.source()?.peek()?.readUtf8()
        } catch (e: IOException) {
            Log.d(TAG, "${e.message}. Unable to peek errorBody")
            ""
        }
    }

    private fun <T, R> convertResponseErrorBody(
        response: Response<T>,
        converter: Converter<ResponseBody, R>
    ): R? {
        try {
            val responseErrorBody = response.errorBody()

            if (responseErrorBody != null) return converter.convert(responseErrorBody)
        } catch (e: IOException) {
            Log.d(TAG, "Unable to convert errorBody() to JSON")
        }
        return null
    }

    override fun cancelAsyncCall(call: Call<JourneyPlanner.ItineraryResult>?) {
        call?.cancel()
    }

    override fun buildMockRetrofit(): Retrofit = JourneyPlannerApiService.mockRetrofit

    companion object {
        val TAG = this::class.java.simpleName as String
        const val NOT_IDENTIFIED = "notidentified"
        const val DISAMBIGUATION_OPTIONS = "disambiguationOptions"
    }
}