package com.my.core.domain

import org.threeten.bp.LocalDateTime

sealed class JourneyPlannerResultDomainModel {
    data class Itinerary(
        val journeyVector: JourneyVector,
        val journeys: List<Journey>
    ) : JourneyPlannerResultDomainModel() {

        data class JourneyVector(
            val from: String,
            val to: String,
            val via: String
        )

        data class Journey(
            val arrivalDateTime: LocalDateTime,
            val duration: Int,
            val legs: List<Leg>,
            val startDateTime: LocalDateTime
        ) {
            data class Leg(
                val arrivalPoint: String,
                val departurePoint: String,
                val duration: Int
            )
        }
    }

    //    TODO("as above")
    data class FromToDisambiguationOptions(
        val toDo: String
    ) : JourneyPlannerResultDomainModel()
}