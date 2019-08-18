package com.my.journeyplanner.models

data class JourneyPlannerDisambiguationResult(
    val fromLocationDisambiguation: FromLocationDisambiguation,
    val journeyVector: JourneyVector,
    val recommendedMaxAgeMinutes: Int,
    val searchCriteria: SearchCriteria,
    val toLocationDisambiguation: ToLocationDisambiguation,
    val type: String,
    val viaLocationDisambiguation: ViaLocationDisambiguation
) {
    data class ViaLocationDisambiguation(
        val matchStatus: String,
        val type: String
    )

    data class FromLocationDisambiguation(
        val disambiguationOptions: List<DisambiguationOption>,
        val matchStatus: String,
        val type: String
    ) {
        data class DisambiguationOption(
            val matchQuality: Int,
            val parameterValue: String,
            val place: Place,
            val type: String,
            val uri: String
        ) {
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
            )
        }
    }

    data class SearchCriteria(
        val dateTime: String,
        val dateTimeType: String,
        val type: String
    )

    data class ToLocationDisambiguation(
        val disambiguationOptions: List<DisambiguationOption>,
        val matchStatus: String,
        val type: String
    ) {
        data class DisambiguationOption(
            val matchQuality: Int,
            val parameterValue: String,
            val place: Place,
            val type: String,
            val uri: String
        ) {
            data class Place(
                val additionalProperties: List<Any>,
                val commonName: String,
                val lat: Double,
                val lon: Double,
                val placeType: String,
                val type: String,
                val url: String
            )
        }
    }

    data class JourneyVector(
        val from: String,
        val to: String,
        val type: String,
        val uri: String,
        val via: String
    )
}