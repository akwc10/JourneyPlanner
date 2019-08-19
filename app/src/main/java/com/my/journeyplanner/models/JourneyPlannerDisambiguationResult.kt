package com.my.journeyplanner.models

import java.lang.reflect.Type

data class JourneyPlannerDisambiguationResult(
    val fromLocationDisambiguation: FromLocationDisambiguation,
    val journeyVector: JourneyVector,
    val recommendedMaxAgeMinutes: Int,
    val searchCriteria: SearchCriteria,
    val toLocationDisambiguation: ToLocationDisambiguation,
    val type: String,
    val viaLocationDisambiguation: ViaLocationDisambiguation
) : Type {
    data class ViaLocationDisambiguation(
        val matchStatus: String,
        val type: String
    ) : Type

    data class FromLocationDisambiguation(
        val disambiguationOptions: List<DisambiguationOption>,
        val matchStatus: String,
        val type: String
    ) : Type {
        data class DisambiguationOption(
            val matchQuality: Int,
            val parameterValue: String,
            val place: Place,
            val type: String,
            val uri: String
        ) : Type {
            data class Place(
                val additionalProperties: List<Any>,
                val commonName: String,
                val icsCode: String,
                val lat: Double,
                val lon: Double,
                val modes: List<String>,
                val naptanId: String,
                val placeType: String,
                val stopType: String,
                val type: String,
                val url: String
            ) : Type
        }
    }

    data class SearchCriteria(
        val dateTime: String,
        val dateTimeType: String,
        val type: String
    ) : Type

    data class ToLocationDisambiguation(
        val disambiguationOptions: List<DisambiguationOption>,
        val matchStatus: String,
        val type: String
    ) : Type {
        data class DisambiguationOption(
            val matchQuality: Int,
            val parameterValue: String,
            val place: Place,
            val type: String,
            val uri: String
        ) : Type {
            data class Place(
                val additionalProperties: List<Any>,
                val commonName: String,
                val lat: Double,
                val lon: Double,
                val placeType: String,
                val type: String,
                val url: String
            ) : Type
        }
    }

    data class JourneyVector(
        val from: String,
        val to: String,
        val type: String,
        val uri: String,
        val via: String
    ) : Type
}