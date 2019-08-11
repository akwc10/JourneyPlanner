package com.my.journeyplanner.models


import com.squareup.moshi.Json

data class JourneyPlannerDisambiguationResult(
    @Json(name = "fromLocationDisambiguation")
    val fromLocationDisambiguation: FromLocationDisambiguation,
    @Json(name = "journeyVector")
    val journeyVector: JourneyVector,
    @Json(name = "recommendedMaxAgeMinutes")
    val recommendedMaxAgeMinutes: Int,
    @Json(name = "searchCriteria")
    val searchCriteria: SearchCriteria,
    @Json(name = "toLocationDisambiguation")
    val toLocationDisambiguation: ToLocationDisambiguation,
//    @Json(name = "$type")
//    val type: String,
    @Json(name = "viaLocationDisambiguation")
    val viaLocationDisambiguation: ViaLocationDisambiguation
) {
    data class ToLocationDisambiguation(
        @Json(name = "disambiguationOptions")
        val disambiguationOptions: List<DisambiguationOption>,
        @Json(name = "matchStatus")
        val matchStatus: String
//        @Json(name = "$type")
//        val type: String
    ) {
        data class DisambiguationOption(
            @Json(name = "matchQuality")
            val matchQuality: Int,
            @Json(name = "parameterValue")
            val parameterValue: String,
            @Json(name = "place")
            val place: Place,
//            @Json(name = "$type")
//            val type: String,
            @Json(name = "uri")
            val uri: String
        ) {
            data class Place(
                @Json(name = "additionalProperties")
                val additionalProperties: List<Any>,
                @Json(name = "commonName")
                val commonName: String,
                @Json(name = "lat")
                val lat: Double,
                @Json(name = "lon")
                val lon: Double,
                @Json(name = "placeType")
                val placeType: String,
//                @Json(name = "$type")
//                val type: String,
                @Json(name = "url")
                val url: String
            )
        }
    }

    data class ViaLocationDisambiguation(
        @Json(name = "matchStatus")
        val matchStatus: String
//        @Json(name = "$type")
//        val type: String
    )

    data class FromLocationDisambiguation(
        @Json(name = "disambiguationOptions")
        val disambiguationOptions: List<DisambiguationOption>,
        @Json(name = "matchStatus")
        val matchStatus: String
//        @Json(name = "$type")
//        val type: String
    ) {
        data class DisambiguationOption(
            @Json(name = "matchQuality")
            val matchQuality: Int,
            @Json(name = "parameterValue")
            val parameterValue: String,
            @Json(name = "place")
            val place: Place,
//            @Json(name = "$type")
//            val type: String,
            @Json(name = "uri")
            val uri: String
        ) {
            data class Place(
                @Json(name = "additionalProperties")
                val additionalProperties: List<Any>,
                @Json(name = "commonName")
                val commonName: String,
                @Json(name = "icsCode")
                val icsCode: String,
                @Json(name = "lat")
                val lat: Double,
                @Json(name = "lon")
                val lon: Double,
                @Json(name = "modes")
                val modes: List<String>,
                @Json(name = "naptanId")
                val naptanId: String,
                @Json(name = "placeType")
                val placeType: String,
                @Json(name = "stopType")
                val stopType: String,
//                @Json(name = "$type")
//                val type: String,
                @Json(name = "url")
                val url: String
            )
        }
    }

    data class SearchCriteria(
        @Json(name = "dateTime")
        val dateTime: String,
        @Json(name = "dateTimeType")
        val dateTimeType: String
//        @Json(name = "$type")
//        val type: String
    )

    data class JourneyVector(
        @Json(name = "from")
        val from: String,
        @Json(name = "to")
        val to: String,
//        @Json(name = "$type")
//        val type: String,
        @Json(name = "uri")
        val uri: String,
        @Json(name = "via")
        val via: String
    )
}