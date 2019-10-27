package com.my.api

import com.my.core.domain.JourneyPlannerResultDomainModel
import org.threeten.bp.LocalDateTime
import retrofit2.Response

fun transformItineraryTO(response: Response<JourneyPlannerResult>): JourneyPlannerResultDomainModel {
    val itineraryResult = response.body() as JourneyPlannerResult.Itinerary
    val itineraryResultJourneyVector = itineraryResult.journeyVector

    return JourneyPlannerResultDomainModel.Itinerary(
        journeyVector = JourneyPlannerResultDomainModel.Itinerary.JourneyVector(
            from = itineraryResultJourneyVector.from,
            to = itineraryResultJourneyVector.to,
            via = itineraryResultJourneyVector.via
        ),
        journeys = itineraryResult.journeys.map { journey ->
            JourneyPlannerResultDomainModel.Itinerary.Journey(
                arrivalDateTime = LocalDateTime.parse(journey.arrivalDateTime),
                legs = journey.legs.map { leg ->
                    JourneyPlannerResultDomainModel.Itinerary.Journey.Leg(
                        instructionSummary = leg.instruction.summary,
                        departureTime = LocalDateTime.parse(leg.departureTime),
                        arrivalTime = LocalDateTime.parse(leg.arrivalTime),
                        departurePoint = leg.departurePoint.commonName,
                        arrivalPoint = leg.arrivalPoint.commonName,
                        path = JourneyPlannerResultDomainModel.Itinerary.Journey.Leg.Path(
                            stopPoints = leg.path.stopPoints.map { it.name }
                        ),
                        mode = leg.mode.name)
                },
                startDateTime = LocalDateTime.parse(journey.startDateTime)
            )
        }
    )
}