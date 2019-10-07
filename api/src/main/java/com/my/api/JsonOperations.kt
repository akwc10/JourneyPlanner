package com.my.api

import org.json.JSONObject

const val TYPE = "\$type"
const val TFL_JOURNEY_PLANNER = "Tfl.Api.Presentation.Entities.JourneyPlanner"
const val ITINERARY_RESULT = "$TFL_JOURNEY_PLANNER.ItineraryResult"
const val DISAMBIGUATION_RESULT = "$TFL_JOURNEY_PLANNER.DisambiguationResult"
const val DISAMBIGUATION_OPTIONS = "disambiguationOptions"

fun JSONObject.isResultType(resultType: String): Boolean =
    this.has(TYPE) && this.getString(TYPE).contains(resultType)