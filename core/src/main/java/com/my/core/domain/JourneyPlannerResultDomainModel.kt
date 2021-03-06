package com.my.core.domain

import org.threeten.bp.LocalDateTime
import java.io.Serializable

sealed class JourneyPlannerResultDomainModel : Serializable {
    data class Itinerary(
        val journeyVector: JourneyVector,
        val journeys: List<Journey>
    ) : JourneyPlannerResultDomainModel() {

        data class JourneyVector(
            val from: String,
            val to: String,
            val via: String
        ) : Serializable

        data class Journey(
            val arrivalDateTime: LocalDateTime,
            val legs: List<Leg>,
            val startDateTime: LocalDateTime
        ) : Serializable {
            data class Leg(
                val instructionSummary: String,
                val departureTime: LocalDateTime,
                val arrivalTime: LocalDateTime,
                val arrivalPoint: String,
                val departurePoint: String,
                val mode: String
            ) : Serializable
        }
    }

    //    TODO("as above")
    data class FromToDisambiguationOptions(
        val toDo: String
    ) : JourneyPlannerResultDomainModel(), Serializable
}