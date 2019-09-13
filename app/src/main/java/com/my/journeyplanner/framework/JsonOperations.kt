package com.my.journeyplanner.framework

import android.util.Log
import org.json.JSONException
import org.json.JSONObject

const val TAG = "JsonOperations"
const val TYPE = "\$type"
const val ITINERARY_RESULT = "ItineraryResult"
const val DISAMBIGUATION_OPTIONS = "disambiguationOptions"
const val FROM_LOCATION_DISAMBIGUATION = "fromLocationDisambiguation"
const val TO_LOCATION_DISAMBIGUATION = "toLocationDisambiguation"
const val MATCH_STATUS = "matchStatus"
const val NOT_IDENTIFIED = "notidentified"

fun JSONObject.isItineraryResult(): Boolean {
    try {
        if (this.has(TYPE)) {
            if (this.getString(TYPE).contains(ITINERARY_RESULT)) return true
        } else {
            Log.d(TAG, "Missing key: $TYPE")
        }
    } catch (e: JSONException) {
        Log.e(TAG, e.message)
    }
    return false
}

fun JSONObject.hasLocationDisambiguationKey(
    hasLocationDisambiguation: Boolean,
    locationDisambiguation: String,
    key: String
): Boolean {
    try {
        if (hasLocationDisambiguation) {
            if (this.getJSONObject(locationDisambiguation).has(key)) {
                when (key) {
                    DISAMBIGUATION_OPTIONS -> {
                        if (this.getJSONObject(locationDisambiguation).getJSONArray(
                                key
                            ).length() > 0
                        ) return true
                    }
                    MATCH_STATUS -> {
                        if (this.getJSONObject(locationDisambiguation).getString(
                                key
                            ) == NOT_IDENTIFIED
                        ) return true
                    }
                }
            } else {
                Log.d(TAG, "Missing key: $locationDisambiguation.$key")
            }
        }
    } catch (e: JSONException) {
        Log.e(TAG, e.message)
    }
    return false
}