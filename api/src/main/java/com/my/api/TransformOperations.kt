package com.my.api

import com.my.core.domain.JourneyPlannerResultDomainModel
import org.threeten.bp.LocalDateTime
import retrofit2.Response

fun transformItineraryTO(response: Response<JourneyPlannerResult>): JourneyPlannerResultDomainModel.Itinerary {
    val itineraryResult = response.body() as JourneyPlannerResult.Itinerary
    val itineraryResultJourneyVector = itineraryResult.journeyVector
    val journeyVector = JourneyPlannerResultDomainModel.Itinerary.JourneyVector(
        from = itineraryResultJourneyVector.from,
        to = itineraryResultJourneyVector.to,
        via = itineraryResultJourneyVector.via
    )
    val journeys =
        mutableListOf<JourneyPlannerResultDomainModel.Itinerary.Journey>()

    itineraryResult.journeys.forEach { journey ->
        val legs =
            mutableListOf<JourneyPlannerResultDomainModel.Itinerary.Journey.Leg>()

        journey.legs.forEach {
            legs.add(
                JourneyPlannerResultDomainModel.Itinerary.Journey.Leg(
                    it.arrivalPoint.commonName,
                    it.departurePoint.commonName,
                    it.duration
                )
            )
        }

        journeys.add(
            JourneyPlannerResultDomainModel.Itinerary.Journey(
                arrivalDateTime = LocalDateTime.parse(journey.arrivalDateTime),
                duration = journey.duration,
                legs = legs,
                startDateTime = LocalDateTime.parse(journey.startDateTime)
            )
        )
    }

    return JourneyPlannerResultDomainModel.Itinerary(
        journeyVector = journeyVector,
        journeys = journeys
    )
}