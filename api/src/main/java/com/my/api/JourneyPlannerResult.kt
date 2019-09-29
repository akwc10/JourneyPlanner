package com.my.api

sealed class JourneyPlannerResult {
    data class Itinerary(
        val `$type`: String,
        val journeyVector: JourneyVector,
        val journeys: List<Journey>,
        val lines: List<Line>,
        val recommendedMaxAgeMinutes: Int,
        val searchCriteria: SearchCriteria,
        val stopMessages: List<Any>
    ) : JourneyPlannerResult() {
        data class SearchCriteria(
            val `$type`: String,
            val dateTime: String,
            val dateTimeType: String,
            val timeAdjustments: TimeAdjustments
        ) {
            data class TimeAdjustments(
                val `$type`: String,
                val earlier: Earlier,
                val earliest: Earliest,
                val later: Later,
                val latest: Latest
            ) {
                data class Latest(
                    val `$type`: String,
                    val date: String,
                    val time: String,
                    val timeIs: String,
                    val uri: String
                )

                data class Earliest(
                    val `$type`: String,
                    val date: String,
                    val time: String,
                    val timeIs: String,
                    val uri: String
                )

                data class Later(
                    val `$type`: String,
                    val date: String,
                    val time: String,
                    val timeIs: String,
                    val uri: String
                )

                data class Earlier(
                    val `$type`: String,
                    val date: String,
                    val time: String,
                    val timeIs: String,
                    val uri: String
                )
            }
        }

        data class JourneyVector(
            val `$type`: String,
            val from: String,
            val to: String,
            val uri: String,
            val via: String
        )

        data class Line(
            val `$type`: String,
            val created: String,
            val crowding: Crowding,
            val disruptions: List<Any>,
            val id: String,
            val lineStatuses: List<LineStatus>,
            val modeName: String,
            val modified: String,
            val name: String,
            val routeSections: List<Any>,
            val serviceTypes: List<ServiceType>
        ) {
            data class LineStatus(
                val `$type`: String,
                val created: String,
                val id: Int,
                val statusSeverity: Int,
                val statusSeverityDescription: String,
                val validityPeriods: List<Any>
            )

            data class ServiceType(
                val `$type`: String,
                val name: String,
                val uri: String
            )

            data class Crowding(
                val `$type`: String
            )
        }

        data class Journey(
            val `$type`: String,
            val arrivalDateTime: String,
            val duration: Int,
            val fare: Fare,
            val legs: List<Leg>,
            val startDateTime: String
        ) {
            data class Leg(
                val `$type`: String,
                val arrivalPoint: ArrivalPoint,
                val arrivalTime: String,
                val departurePoint: DeparturePoint,
                val departureTime: String,
                val disruptions: List<Any>,
                val distance: Double,
                val duration: Int,
                val hasFixedLocations: Boolean,
                val instruction: Instruction,
                val isDisrupted: Boolean,
                val mode: Mode,
                val obstacles: List<Any>,
                val path: Path,
                val plannedWorks: List<Any>,
                val routeOptions: List<RouteOption>
            ) {
                data class DeparturePoint(
                    val `$type`: String,
                    val additionalProperties: List<Any>,
                    val commonName: String,
                    val icsCode: String,
                    val lat: Double,
                    val lon: Double,
                    val placeType: String,
                    val platformName: String
                )

                data class RouteOption(
                    val `$type`: String,
                    val directions: List<String>,
                    val name: String
                )

                data class Path(
                    val `$type`: String,
                    val elevation: List<Any>,
                    val lineString: String,
                    val stopPoints: List<Any>
                )

                data class ArrivalPoint(
                    val `$type`: String,
                    val additionalProperties: List<Any>,
                    val commonName: String,
                    val icsCode: String,
                    val lat: Double,
                    val lon: Double,
                    val placeType: String,
                    val platformName: String
                )

                data class Mode(
                    val `$type`: String,
                    val id: String,
                    val name: String,
                    val routeType: String,
                    val status: String,
                    val type: String
                )

                data class Instruction(
                    val `$type`: String,
                    val detailed: String,
                    val steps: List<Step>,
                    val summary: String
                ) {
                    data class Step(
                        val `$type`: String,
                        val cumulativeDistance: Int,
                        val cumulativeTravelTime: Int,
                        val description: String,
                        val descriptionHeading: String,
                        val distance: Int,
                        val latitude: Double,
                        val longitude: Double,
                        val pathAttribute: PathAttribute,
                        val skyDirection: Int,
                        val skyDirectionDescription: String,
                        val streetName: String,
                        val trackType: String,
                        val turnDirection: String
                    ) {
                        data class PathAttribute(
                            val `$type`: String
                        )
                    }
                }
            }

            data class Fare(
                val `$type`: String,
                val caveats: List<Caveat>,
                val fares: List<Fare>,
                val totalCost: Int
            ) {
                data class Caveat(
                    val `$type`: String,
                    val text: String,
                    val type: String
                )

                data class Fare(
                    val `$type`: String,
                    val chargeLevel: String,
                    val chargeProfileName: String,
                    val cost: Int,
                    val highZone: Int,
                    val isHopperFare: Boolean,
                    val lowZone: Int,
                    val offPeak: Int,
                    val peak: Int,
                    val taps: List<Tap>
                ) {
                    data class Tap(
                        val `$type`: String,
                        val atcoCode: String,
                        val tapDetails: TapDetails
                    ) {
                        data class TapDetails(
                            val `$type`: String,
                            val hostDeviceType: String,
                            val modeType: String,
                            val nationalLocationCode: Int,
                            val tapTimestamp: String,
                            val validationType: String
                        )
                    }
                }
            }
        }
    }

    data class FromToDisambiguationOptions(
        val fromLocationDisambiguation: FromLocationDisambiguation,
        val journeyVector: JourneyVector,
        val recommendedMaxAgeMinutes: Int,
        val searchCriteria: SearchCriteria,
        val toLocationDisambiguation: ToLocationDisambiguation,
        val `$type`: String,
        val viaLocationDisambiguation: ViaLocationDisambiguation
    ) : JourneyPlannerResult() {
        data class ViaLocationDisambiguation(
            val matchStatus: String,
            val `$type`: String
        )

        data class FromLocationDisambiguation(
            val disambiguationOptions: List<DisambiguationOption>?,
            val matchStatus: String,
            val `$type`: String
        ) {
            data class DisambiguationOption(
                val matchQuality: Int,
                val parameterValue: String,
                val place: Place,
                val `$type`: String,
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
                    val `$type`: String,
                    val url: String
                )
            }
        }

        data class SearchCriteria(
            val dateTime: String,
            val dateTimeType: String,
            val `$type`: String
        )

        data class ToLocationDisambiguation(
            val disambiguationOptions: List<DisambiguationOption>?,
            val matchStatus: String,
            val `$type`: String
        ) {
            data class DisambiguationOption(
                val matchQuality: Int,
                val parameterValue: String,
                val place: Place,
                val `$type`: String,
                val uri: String
            ) {
                data class Place(
                    val additionalProperties: List<Any>,
                    val commonName: String,
                    val lat: Double,
                    val lon: Double,
                    val placeType: String,
                    val `$type`: String,
                    val url: String
                )
            }
        }

        data class JourneyVector(
            val from: String,
            val to: String,
            val `$type`: String,
            val uri: String,
            val via: String
        )
    }
}