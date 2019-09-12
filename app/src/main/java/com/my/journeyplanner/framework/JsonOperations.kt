package com.my.journeyplanner.framework

import android.util.Log
import org.json.JSONException
import org.json.JSONObject

const val TAG = "JsonOperations"
const val TYPE = "\$type"
const val ITINERARY_RESULT = "ItineraryResult"
const val DISAMBIGUATION_OPTIONS = "DisambiguationOptions"
const val FROM_LOCATION_DISAMBIGUATION = "fromLocationDisambiguation"
const val TO_LOCATION_DISAMBIGUATION = "toLocationDisambiguation"
const val MATCH_STATUS = "matchStatus"
const val NOT_IDENTIFIED = "notidentified"

fun isItineraryResult(jsonBody: JSONObject): Boolean {
    try {
        if (jsonBody.has(TYPE)) {
            if (jsonBody.getString(TYPE).contains(
                    ITINERARY_RESULT
                )
            ) return true
        }
    } catch (e: JSONException) {
        Log.e(JourneyPlannerResultConverterFactory.TAG, e.message)
    }
    return false
}

fun hasLocationDisambiguationKey(
    hasLocationDisambiguation: Boolean,
    jsonBody: JSONObject,
    locationDisambiguation: String,
    key: String
): Boolean {
    try {
        if (hasLocationDisambiguation) {
            if (jsonBody.getJSONObject(locationDisambiguation).has(key)) {
                when (key) {
                    DISAMBIGUATION_OPTIONS -> {
                        if (jsonBody.getJSONObject(locationDisambiguation).getJSONArray(
                                key
                            ).length() > 0
                        ) return true
                    }
                    MATCH_STATUS -> {
                        if (jsonBody.getJSONObject(locationDisambiguation).getString(
                                key
                            ) == NOT_IDENTIFIED
                        ) return true
                    }
                }
            }
        }
    } catch (e: JSONException) {
        Log.e(TAG, e.message)
    }
    return false
}