package com.my.journeyplanner.features.searchquery.framework

import org.json.JSONObject

const val TYPE = "\$type"
const val ITINERARY_RESULT = "ItineraryResult"
const val DISAMBIGUATION_RESULT = "DisambiguationResult"

fun JSONObject.isResultType(resultType: String): Boolean {
    if (this.has(TYPE)) {
        if (this.getString(TYPE).contains(resultType)) return true
    }
    return false
}