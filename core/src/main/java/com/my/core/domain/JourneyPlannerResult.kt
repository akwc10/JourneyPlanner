package com.my.core.domain

import java.io.Serializable

sealed class JourneyPlannerResult {
    data class Itinerary(
        val `$type`: String,
        val journeyVector: JourneyVector,
        val journeys: List<Journey>,
        val lines: List<Line>,
        val recommendedMaxAgeMinutes: Int,
        val searchCriteria: SearchCriteria,
        val stopMessages: List<Any>
    ) : JourneyPlannerResult(), Serializable {
        data class SearchCriteria(
            val `$type`: String,
            val dateTime: String,
            val dateTimeType: String,
            val timeAdjustments: TimeAdjustments
        ) : Serializable {
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
                ) : Serializable

                data class Earliest(
                    val `$type`: String,
                    val date: String,
                    val time: String,
                    val timeIs: String,
                    val uri: String
                ) : Serializable

                data class Later(
                    val `$type`: String,
                    val date: String,
                    val time: String,
                    val timeIs: String,
                    val uri: String
                ) : Serializable

                data class Earlier(
                    val `$type`: String,
                    val date: String,
                    val time: String,
                    val timeIs: String,
                    val uri: String
                ) : Serializable
            }
        }

        data class JourneyVector(
            val `$type`: String,
            val from: String,
            val to: String,
            val uri: String,
            val via: String
        ) : Serializable

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
        ) : Serializable {
            data class LineStatus(
                val `$type`: String,
                val created: String,
                val id: Int,
                val statusSeverity: Int,
                val statusSeverityDescription: String,
                val validityPeriods: List<Any>
            ) : Serializable

            data class ServiceType(
                val `$type`: String,
                val name: String,
                val uri: String
            ) : Serializable

            data class Crowding(
                val `$type`: String
            ) : Serializable
        }

        data class Journey(
            val `$type`: String,
            val arrivalDateTime: String,
            val duration: Int,
            val fare: Fare,
            val legs: List<Leg>,
            val startDateTime: String
        ) : Serializable {
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
            ) : Serializable {
                data class DeparturePoint(
                    val `$type`: String,
                    val additionalProperties: List<Any>,
                    val commonName: String,
                    val icsCode: String,
                    val lat: Double,
                    val lon: Double,
                    val placeType: String,
                    val platformName: String
                ) : Serializable

                data class RouteOption(
                    val `$type`: String,
                    val directions: List<String>,
                    val name: String
                ) : Serializable

                data class Path(
                    val `$type`: String,
                    val elevation: List<Any>,
                    val lineString: String,
                    val stopPoints: List<Any>
                ) : Serializable

                data class ArrivalPoint(
                    val `$type`: String,
                    val additionalProperties: List<Any>,
                    val commonName: String,
                    val icsCode: String,
                    val lat: Double,
                    val lon: Double,
                    val placeType: String,
                    val platformName: String
                ) : Serializable

                data class Mode(
                    val `$type`: String,
                    val id: String,
                    val name: String,
                    val routeType: String,
                    val status: String,
                    val type: String
                ) : Serializable

                data class Instruction(
                    val `$type`: String,
                    val detailed: String,
                    val steps: List<Step>,
                    val summary: String
                ) : Serializable {
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
                    ) : Serializable {
                        data class PathAttribute(
                            val `$type`: String
                        ) : Serializable
                    }
                }
            }

            data class Fare(
                val `$type`: String,
                val caveats: List<Caveat>,
                val fares: List<Fare>,
                val totalCost: Int
            ) : Serializable {
                data class Caveat(
                    val `$type`: String,
                    val text: String,
                    val type: String
                ) : Serializable

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
                ) : Serializable {
                    data class Tap(
                        val `$type`: String,
                        val atcoCode: String,
                        val tapDetails: TapDetails
                    ) : Serializable {
                        data class TapDetails(
                            val `$type`: String,
                            val hostDeviceType: String,
                            val modeType: String,
                            val nationalLocationCode: Int,
                            val tapTimestamp: String,
                            val validationType: String
                        ) : Serializable
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
    ) : JourneyPlannerResult(), Serializable {
        data class ViaLocationDisambiguation(
            val matchStatus: String,
            val `$type`: String
        ) : Serializable

        data class FromLocationDisambiguation(
            val disambiguationOptions: List<DisambiguationOption>?,
            val matchStatus: String,
            val `$type`: String
        ) : Serializable {
            data class DisambiguationOption(
                val matchQuality: Int,
                val parameterValue: String,
                val place: Place,
                val `$type`: String,
                val uri: String
            ) : Serializable {
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
                ) : Serializable
            }
        }

        data class SearchCriteria(
            val dateTime: String,
            val dateTimeType: String,
            val `$type`: String
        ) : Serializable

        data class ToLocationDisambiguation(
            val disambiguationOptions: List<DisambiguationOption>?,
            val matchStatus: String,
            val `$type`: String
        ) : Serializable {
            data class DisambiguationOption(
                val matchQuality: Int,
                val parameterValue: String,
                val place: Place,
                val `$type`: String,
                val uri: String
            ) : Serializable {
                data class Place(
                    val additionalProperties: List<Any>,
                    val commonName: String,
                    val lat: Double,
                    val lon: Double,
                    val placeType: String,
                    val `$type`: String,
                    val url: String
                ) : Serializable
            }
        }

        data class JourneyVector(
            val from: String,
            val to: String,
            val `$type`: String,
            val uri: String,
            val via: String
        ) : Serializable
    }
}