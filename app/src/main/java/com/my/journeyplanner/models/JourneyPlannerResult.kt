package com.my.journeyplanner.models


import com.squareup.moshi.Json

data class JourneyPlannerResult(
    @Json(name = "cycleHireDockingStationData")
    val cycleHireDockingStationData: CycleHireDockingStationData,
    @Json(name = "journeyVector")
    val journeyVector: JourneyVector,
    @Json(name = "journeys")
    val journeys: List<Journey>,
    @Json(name = "lines")
    val lines: List<Line>,
    @Json(name = "recommendedMaxAgeMinutes")
    val recommendedMaxAgeMinutes: Int,
    @Json(name = "searchCriteria")
    val searchCriteria: SearchCriteria,
    @Json(name = "stopMessages")
    val stopMessages: List<String>
) {
    data class CycleHireDockingStationData(
        @Json(name = "destinationId")
        val destinationId: String,
        @Json(name = "destinationNumberOfBikes")
        val destinationNumberOfBikes: Int,
        @Json(name = "destinationNumberOfEmptySlots")
        val destinationNumberOfEmptySlots: Int,
        @Json(name = "originId")
        val originId: String,
        @Json(name = "originNumberOfBikes")
        val originNumberOfBikes: Int,
        @Json(name = "originNumberOfEmptySlots")
        val originNumberOfEmptySlots: Int
    )

    data class SearchCriteria(
        @Json(name = "dateTime")
        val dateTime: String,
        @Json(name = "dateTimeType")
        val dateTimeType: String,
        @Json(name = "timeAdjustments")
        val timeAdjustments: TimeAdjustments
    ) {
        data class TimeAdjustments(
            @Json(name = "earlier")
            val earlier: Earlier,
            @Json(name = "earliest")
            val earliest: Earliest,
            @Json(name = "later")
            val later: Later,
            @Json(name = "latest")
            val latest: Latest
        ) {
            data class Latest(
                @Json(name = "date")
                val date: String,
                @Json(name = "time")
                val time: String,
                @Json(name = "timeIs")
                val timeIs: String,
                @Json(name = "uri")
                val uri: String
            )

            data class Earliest(
                @Json(name = "date")
                val date: String,
                @Json(name = "time")
                val time: String,
                @Json(name = "timeIs")
                val timeIs: String,
                @Json(name = "uri")
                val uri: String
            )

            data class Earlier(
                @Json(name = "date")
                val date: String,
                @Json(name = "time")
                val time: String,
                @Json(name = "timeIs")
                val timeIs: String,
                @Json(name = "uri")
                val uri: String
            )

            data class Later(
                @Json(name = "date")
                val date: String,
                @Json(name = "time")
                val time: String,
                @Json(name = "timeIs")
                val timeIs: String,
                @Json(name = "uri")
                val uri: String
            )
        }
    }

    data class Line(
        @Json(name = "created")
        val created: String,
        @Json(name = "crowding")
        val crowding: Crowding,
        @Json(name = "disruptions")
        val disruptions: List<Disruption>,
        @Json(name = "id")
        val id: String,
        @Json(name = "lineStatuses")
        val lineStatuses: List<LineStatuse>,
        @Json(name = "modeName")
        val modeName: String,
        @Json(name = "modified")
        val modified: String,
        @Json(name = "name")
        val name: String,
        @Json(name = "routeSections")
        val routeSections: List<RouteSection>,
        @Json(name = "serviceTypes")
        val serviceTypes: List<ServiceType>
    ) {
        data class Crowding(
            @Json(name = "passengerFlows")
            val passengerFlows: List<PassengerFlow>,
            @Json(name = "trainLoadings")
            val trainLoadings: List<TrainLoading>
        ) {
            data class TrainLoading(
                @Json(name = "direction")
                val direction: String,
                @Json(name = "line")
                val line: String,
                @Json(name = "lineDirection")
                val lineDirection: String,
                @Json(name = "naptanTo")
                val naptanTo: String,
                @Json(name = "platformDirection")
                val platformDirection: String,
                @Json(name = "timeSlice")
                val timeSlice: String,
                @Json(name = "value")
                val value: Int
            )

            data class PassengerFlow(
                @Json(name = "timeSlice")
                val timeSlice: String,
                @Json(name = "value")
                val value: Int
            )
        }

        data class RouteSection(
            @Json(name = "destination")
            val destination: String,
            @Json(name = "destinationName")
            val destinationName: String,
            @Json(name = "direction")
            val direction: String,
            @Json(name = "name")
            val name: String,
            @Json(name = "originationName")
            val originationName: String,
            @Json(name = "originator")
            val originator: String,
            @Json(name = "routeCode")
            val routeCode: String,
            @Json(name = "serviceType")
            val serviceType: String,
            @Json(name = "validFrom")
            val validFrom: String,
            @Json(name = "validTo")
            val validTo: String
        )

        data class ServiceType(
            @Json(name = "name")
            val name: String,
            @Json(name = "uri")
            val uri: String
        )

        data class Disruption(
            @Json(name = "additionalInfo")
            val additionalInfo: String,
            @Json(name = "affectedRoutes")
            val affectedRoutes: List<AffectedRoute>,
            @Json(name = "affectedStops")
            val affectedStops: List<AffectedStop>,
            @Json(name = "category")
            val category: String,
            @Json(name = "categoryDescription")
            val categoryDescription: String,
            @Json(name = "closureText")
            val closureText: String,
            @Json(name = "created")
            val created: String,
            @Json(name = "description")
            val description: String,
            @Json(name = "lastUpdate")
            val lastUpdate: String,
            @Json(name = "summary")
            val summary: String,
            @Json(name = "type")
            val type: String
        ) {
            data class AffectedRoute(
                @Json(name = "destinationName")
                val destinationName: String,
                @Json(name = "direction")
                val direction: String,
                @Json(name = "id")
                val id: String,
                @Json(name = "lineId")
                val lineId: String,
                @Json(name = "lineString")
                val lineString: String,
                @Json(name = "name")
                val name: String,
                @Json(name = "originationName")
                val originationName: String,
                @Json(name = "routeCode")
                val routeCode: String,
                @Json(name = "routeSectionNaptanEntrySequence")
                val routeSectionNaptanEntrySequence: List<RouteSectionNaptanEntrySequence>,
                @Json(name = "validFrom")
                val validFrom: String,
                @Json(name = "validTo")
                val validTo: String
            ) {
                data class RouteSectionNaptanEntrySequence(
                    @Json(name = "ordinal")
                    val ordinal: Int,
                    @Json(name = "stopPoint")
                    val stopPoint: StopPoint
                ) {
                    data class StopPoint(
                        @Json(name = "accessibilitySummary")
                        val accessibilitySummary: String,
                        @Json(name = "additionalProperties")
                        val additionalProperties: List<AdditionalProperty>,
                        @Json(name = "children")
                        val children: List<Children>,
                        @Json(name = "childrenUrls")
                        val childrenUrls: List<String>,
                        @Json(name = "commonName")
                        val commonName: String,
                        @Json(name = "distance")
                        val distance: Int,
                        @Json(name = "fullName")
                        val fullName: String,
                        @Json(name = "hubNaptanCode")
                        val hubNaptanCode: String,
                        @Json(name = "icsCode")
                        val icsCode: String,
                        @Json(name = "id")
                        val id: String,
                        @Json(name = "indicator")
                        val indicator: String,
                        @Json(name = "lat")
                        val lat: Int,
                        @Json(name = "lineGroup")
                        val lineGroup: List<LineGroup>,
                        @Json(name = "lineModeGroups")
                        val lineModeGroups: List<LineModeGroup>,
                        @Json(name = "lines")
                        val lines: List<Line>,
                        @Json(name = "lon")
                        val lon: Int,
                        @Json(name = "modes")
                        val modes: List<String>,
                        @Json(name = "naptanId")
                        val naptanId: String,
                        @Json(name = "naptanMode")
                        val naptanMode: String,
                        @Json(name = "placeType")
                        val placeType: String,
                        @Json(name = "platformName")
                        val platformName: String,
                        @Json(name = "smsCode")
                        val smsCode: String,
                        @Json(name = "stationNaptan")
                        val stationNaptan: String,
                        @Json(name = "status")
                        val status: Boolean,
                        @Json(name = "stopLetter")
                        val stopLetter: String,
                        @Json(name = "stopType")
                        val stopType: String,
                        @Json(name = "url")
                        val url: String
                    ) {
                        data class Line(
                            @Json(name = "crowding")
                            val crowding: Crowding,
                            @Json(name = "fullName")
                            val fullName: String,
                            @Json(name = "id")
                            val id: String,
                            @Json(name = "name")
                            val name: String,
                            @Json(name = "routeType")
                            val routeType: String,
                            @Json(name = "status")
                            val status: String,
                            @Json(name = "type")
                            val type: String,
                            @Json(name = "uri")
                            val uri: String
                        ) {
                            data class Crowding(
                                @Json(name = "passengerFlows")
                                val passengerFlows: List<PassengerFlow>,
                                @Json(name = "trainLoadings")
                                val trainLoadings: List<TrainLoading>
                            ) {
                                data class TrainLoading(
                                    @Json(name = "direction")
                                    val direction: String,
                                    @Json(name = "line")
                                    val line: String,
                                    @Json(name = "lineDirection")
                                    val lineDirection: String,
                                    @Json(name = "naptanTo")
                                    val naptanTo: String,
                                    @Json(name = "platformDirection")
                                    val platformDirection: String,
                                    @Json(name = "timeSlice")
                                    val timeSlice: String,
                                    @Json(name = "value")
                                    val value: Int
                                )

                                data class PassengerFlow(
                                    @Json(name = "timeSlice")
                                    val timeSlice: String,
                                    @Json(name = "value")
                                    val value: Int
                                )
                            }
                        }

                        data class LineGroup(
                            @Json(name = "lineIdentifier")
                            val lineIdentifier: List<String>,
                            @Json(name = "naptanIdReference")
                            val naptanIdReference: String,
                            @Json(name = "stationAtcoCode")
                            val stationAtcoCode: String
                        )

                        data class AdditionalProperty(
                            @Json(name = "category")
                            val category: String,
                            @Json(name = "key")
                            val key: String,
                            @Json(name = "modified")
                            val modified: String,
                            @Json(name = "sourceSystemKey")
                            val sourceSystemKey: String,
                            @Json(name = "value")
                            val value: String
                        )

                        data class LineModeGroup(
                            @Json(name = "lineIdentifier")
                            val lineIdentifier: List<String>,
                            @Json(name = "modeName")
                            val modeName: String
                        )

                        data class Children(
                            @Json(name = "additionalProperties")
                            val additionalProperties: List<AdditionalProperty>,
                            @Json(name = "children")
                            val children: List<Any>,
                            @Json(name = "childrenUrls")
                            val childrenUrls: List<String>,
                            @Json(name = "commonName")
                            val commonName: String,
                            @Json(name = "distance")
                            val distance: Int,
                            @Json(name = "id")
                            val id: String,
                            @Json(name = "lat")
                            val lat: Int,
                            @Json(name = "lon")
                            val lon: Int,
                            @Json(name = "placeType")
                            val placeType: String,
                            @Json(name = "url")
                            val url: String
                        ) {
                            data class AdditionalProperty(
                                @Json(name = "category")
                                val category: String,
                                @Json(name = "key")
                                val key: String,
                                @Json(name = "modified")
                                val modified: String,
                                @Json(name = "sourceSystemKey")
                                val sourceSystemKey: String,
                                @Json(name = "value")
                                val value: String
                            )
                        }
                    }
                }
            }

            data class AffectedStop(
                @Json(name = "accessibilitySummary")
                val accessibilitySummary: String,
                @Json(name = "additionalProperties")
                val additionalProperties: List<AdditionalProperty>,
                @Json(name = "children")
                val children: List<Children>,
                @Json(name = "childrenUrls")
                val childrenUrls: List<String>,
                @Json(name = "commonName")
                val commonName: String,
                @Json(name = "distance")
                val distance: Int,
                @Json(name = "fullName")
                val fullName: String,
                @Json(name = "hubNaptanCode")
                val hubNaptanCode: String,
                @Json(name = "icsCode")
                val icsCode: String,
                @Json(name = "id")
                val id: String,
                @Json(name = "indicator")
                val indicator: String,
                @Json(name = "lat")
                val lat: Int,
                @Json(name = "lineGroup")
                val lineGroup: List<LineGroup>,
                @Json(name = "lineModeGroups")
                val lineModeGroups: List<LineModeGroup>,
                @Json(name = "lines")
                val lines: List<Line>,
                @Json(name = "lon")
                val lon: Int,
                @Json(name = "modes")
                val modes: List<String>,
                @Json(name = "naptanId")
                val naptanId: String,
                @Json(name = "naptanMode")
                val naptanMode: String,
                @Json(name = "placeType")
                val placeType: String,
                @Json(name = "platformName")
                val platformName: String,
                @Json(name = "smsCode")
                val smsCode: String,
                @Json(name = "stationNaptan")
                val stationNaptan: String,
                @Json(name = "status")
                val status: Boolean,
                @Json(name = "stopLetter")
                val stopLetter: String,
                @Json(name = "stopType")
                val stopType: String,
                @Json(name = "url")
                val url: String
            ) {
                data class Line(
                    @Json(name = "crowding")
                    val crowding: Crowding,
                    @Json(name = "fullName")
                    val fullName: String,
                    @Json(name = "id")
                    val id: String,
                    @Json(name = "name")
                    val name: String,
                    @Json(name = "routeType")
                    val routeType: String,
                    @Json(name = "status")
                    val status: String,
                    @Json(name = "type")
                    val type: String,
                    @Json(name = "uri")
                    val uri: String
                ) {
                    data class Crowding(
                        @Json(name = "passengerFlows")
                        val passengerFlows: List<PassengerFlow>,
                        @Json(name = "trainLoadings")
                        val trainLoadings: List<TrainLoading>
                    ) {
                        data class TrainLoading(
                            @Json(name = "direction")
                            val direction: String,
                            @Json(name = "line")
                            val line: String,
                            @Json(name = "lineDirection")
                            val lineDirection: String,
                            @Json(name = "naptanTo")
                            val naptanTo: String,
                            @Json(name = "platformDirection")
                            val platformDirection: String,
                            @Json(name = "timeSlice")
                            val timeSlice: String,
                            @Json(name = "value")
                            val value: Int
                        )

                        data class PassengerFlow(
                            @Json(name = "timeSlice")
                            val timeSlice: String,
                            @Json(name = "value")
                            val value: Int
                        )
                    }
                }

                data class AdditionalProperty(
                    @Json(name = "category")
                    val category: String,
                    @Json(name = "key")
                    val key: String,
                    @Json(name = "modified")
                    val modified: String,
                    @Json(name = "sourceSystemKey")
                    val sourceSystemKey: String,
                    @Json(name = "value")
                    val value: String
                )

                data class LineGroup(
                    @Json(name = "lineIdentifier")
                    val lineIdentifier: List<String>,
                    @Json(name = "naptanIdReference")
                    val naptanIdReference: String,
                    @Json(name = "stationAtcoCode")
                    val stationAtcoCode: String
                )

                data class Children(
                    @Json(name = "additionalProperties")
                    val additionalProperties: List<AdditionalProperty>,
                    @Json(name = "children")
                    val children: List<Any>,
                    @Json(name = "childrenUrls")
                    val childrenUrls: List<String>,
                    @Json(name = "commonName")
                    val commonName: String,
                    @Json(name = "distance")
                    val distance: Int,
                    @Json(name = "id")
                    val id: String,
                    @Json(name = "lat")
                    val lat: Int,
                    @Json(name = "lon")
                    val lon: Int,
                    @Json(name = "placeType")
                    val placeType: String,
                    @Json(name = "url")
                    val url: String
                ) {
                    data class AdditionalProperty(
                        @Json(name = "category")
                        val category: String,
                        @Json(name = "key")
                        val key: String,
                        @Json(name = "modified")
                        val modified: String,
                        @Json(name = "sourceSystemKey")
                        val sourceSystemKey: String,
                        @Json(name = "value")
                        val value: String
                    )
                }

                data class LineModeGroup(
                    @Json(name = "lineIdentifier")
                    val lineIdentifier: List<String>,
                    @Json(name = "modeName")
                    val modeName: String
                )
            }
        }

        data class LineStatuse(
            @Json(name = "created")
            val created: String,
            @Json(name = "disruption")
            val disruption: Disruption,
            @Json(name = "id")
            val id: Int,
            @Json(name = "lineId")
            val lineId: String,
            @Json(name = "modified")
            val modified: String,
            @Json(name = "reason")
            val reason: String,
            @Json(name = "statusSeverity")
            val statusSeverity: Int,
            @Json(name = "statusSeverityDescription")
            val statusSeverityDescription: String,
            @Json(name = "validityPeriods")
            val validityPeriods: List<ValidityPeriod>
        ) {
            data class ValidityPeriod(
                @Json(name = "fromDate")
                val fromDate: String,
                @Json(name = "isNow")
                val isNow: Boolean,
                @Json(name = "toDate")
                val toDate: String
            )

            data class Disruption(
                @Json(name = "additionalInfo")
                val additionalInfo: String,
                @Json(name = "affectedRoutes")
                val affectedRoutes: List<AffectedRoute>,
                @Json(name = "affectedStops")
                val affectedStops: List<AffectedStop>,
                @Json(name = "category")
                val category: String,
                @Json(name = "categoryDescription")
                val categoryDescription: String,
                @Json(name = "closureText")
                val closureText: String,
                @Json(name = "created")
                val created: String,
                @Json(name = "description")
                val description: String,
                @Json(name = "lastUpdate")
                val lastUpdate: String,
                @Json(name = "summary")
                val summary: String,
                @Json(name = "type")
                val type: String
            ) {
                data class AffectedStop(
                    @Json(name = "accessibilitySummary")
                    val accessibilitySummary: String,
                    @Json(name = "additionalProperties")
                    val additionalProperties: List<AdditionalProperty>,
                    @Json(name = "children")
                    val children: List<Children>,
                    @Json(name = "childrenUrls")
                    val childrenUrls: List<String>,
                    @Json(name = "commonName")
                    val commonName: String,
                    @Json(name = "distance")
                    val distance: Int,
                    @Json(name = "fullName")
                    val fullName: String,
                    @Json(name = "hubNaptanCode")
                    val hubNaptanCode: String,
                    @Json(name = "icsCode")
                    val icsCode: String,
                    @Json(name = "id")
                    val id: String,
                    @Json(name = "indicator")
                    val indicator: String,
                    @Json(name = "lat")
                    val lat: Int,
                    @Json(name = "lineGroup")
                    val lineGroup: List<LineGroup>,
                    @Json(name = "lineModeGroups")
                    val lineModeGroups: List<LineModeGroup>,
                    @Json(name = "lines")
                    val lines: List<Line>,
                    @Json(name = "lon")
                    val lon: Int,
                    @Json(name = "modes")
                    val modes: List<String>,
                    @Json(name = "naptanId")
                    val naptanId: String,
                    @Json(name = "naptanMode")
                    val naptanMode: String,
                    @Json(name = "placeType")
                    val placeType: String,
                    @Json(name = "platformName")
                    val platformName: String,
                    @Json(name = "smsCode")
                    val smsCode: String,
                    @Json(name = "stationNaptan")
                    val stationNaptan: String,
                    @Json(name = "status")
                    val status: Boolean,
                    @Json(name = "stopLetter")
                    val stopLetter: String,
                    @Json(name = "stopType")
                    val stopType: String,
                    @Json(name = "url")
                    val url: String
                ) {
                    data class Line(
                        @Json(name = "crowding")
                        val crowding: Crowding,
                        @Json(name = "fullName")
                        val fullName: String,
                        @Json(name = "id")
                        val id: String,
                        @Json(name = "name")
                        val name: String,
                        @Json(name = "routeType")
                        val routeType: String,
                        @Json(name = "status")
                        val status: String,
                        @Json(name = "type")
                        val type: String,
                        @Json(name = "uri")
                        val uri: String
                    ) {
                        data class Crowding(
                            @Json(name = "passengerFlows")
                            val passengerFlows: List<PassengerFlow>,
                            @Json(name = "trainLoadings")
                            val trainLoadings: List<TrainLoading>
                        ) {
                            data class TrainLoading(
                                @Json(name = "direction")
                                val direction: String,
                                @Json(name = "line")
                                val line: String,
                                @Json(name = "lineDirection")
                                val lineDirection: String,
                                @Json(name = "naptanTo")
                                val naptanTo: String,
                                @Json(name = "platformDirection")
                                val platformDirection: String,
                                @Json(name = "timeSlice")
                                val timeSlice: String,
                                @Json(name = "value")
                                val value: Int
                            )

                            data class PassengerFlow(
                                @Json(name = "timeSlice")
                                val timeSlice: String,
                                @Json(name = "value")
                                val value: Int
                            )
                        }
                    }

                    data class AdditionalProperty(
                        @Json(name = "category")
                        val category: String,
                        @Json(name = "key")
                        val key: String,
                        @Json(name = "modified")
                        val modified: String,
                        @Json(name = "sourceSystemKey")
                        val sourceSystemKey: String,
                        @Json(name = "value")
                        val value: String
                    )

                    data class LineGroup(
                        @Json(name = "lineIdentifier")
                        val lineIdentifier: List<String>,
                        @Json(name = "naptanIdReference")
                        val naptanIdReference: String,
                        @Json(name = "stationAtcoCode")
                        val stationAtcoCode: String
                    )

                    data class Children(
                        @Json(name = "additionalProperties")
                        val additionalProperties: List<AdditionalProperty>,
                        @Json(name = "children")
                        val children: List<Any>,
                        @Json(name = "childrenUrls")
                        val childrenUrls: List<String>,
                        @Json(name = "commonName")
                        val commonName: String,
                        @Json(name = "distance")
                        val distance: Int,
                        @Json(name = "id")
                        val id: String,
                        @Json(name = "lat")
                        val lat: Int,
                        @Json(name = "lon")
                        val lon: Int,
                        @Json(name = "placeType")
                        val placeType: String,
                        @Json(name = "url")
                        val url: String
                    ) {
                        data class AdditionalProperty(
                            @Json(name = "category")
                            val category: String,
                            @Json(name = "key")
                            val key: String,
                            @Json(name = "modified")
                            val modified: String,
                            @Json(name = "sourceSystemKey")
                            val sourceSystemKey: String,
                            @Json(name = "value")
                            val value: String
                        )
                    }

                    data class LineModeGroup(
                        @Json(name = "lineIdentifier")
                        val lineIdentifier: List<String>,
                        @Json(name = "modeName")
                        val modeName: String
                    )
                }

                data class AffectedRoute(
                    @Json(name = "destinationName")
                    val destinationName: String,
                    @Json(name = "direction")
                    val direction: String,
                    @Json(name = "id")
                    val id: String,
                    @Json(name = "lineId")
                    val lineId: String,
                    @Json(name = "lineString")
                    val lineString: String,
                    @Json(name = "name")
                    val name: String,
                    @Json(name = "originationName")
                    val originationName: String,
                    @Json(name = "routeCode")
                    val routeCode: String,
                    @Json(name = "routeSectionNaptanEntrySequence")
                    val routeSectionNaptanEntrySequence: List<RouteSectionNaptanEntrySequence>,
                    @Json(name = "validFrom")
                    val validFrom: String,
                    @Json(name = "validTo")
                    val validTo: String
                ) {
                    data class RouteSectionNaptanEntrySequence(
                        @Json(name = "ordinal")
                        val ordinal: Int,
                        @Json(name = "stopPoint")
                        val stopPoint: StopPoint
                    ) {
                        data class StopPoint(
                            @Json(name = "accessibilitySummary")
                            val accessibilitySummary: String,
                            @Json(name = "additionalProperties")
                            val additionalProperties: List<AdditionalProperty>,
                            @Json(name = "children")
                            val children: List<Children>,
                            @Json(name = "childrenUrls")
                            val childrenUrls: List<String>,
                            @Json(name = "commonName")
                            val commonName: String,
                            @Json(name = "distance")
                            val distance: Int,
                            @Json(name = "fullName")
                            val fullName: String,
                            @Json(name = "hubNaptanCode")
                            val hubNaptanCode: String,
                            @Json(name = "icsCode")
                            val icsCode: String,
                            @Json(name = "id")
                            val id: String,
                            @Json(name = "indicator")
                            val indicator: String,
                            @Json(name = "lat")
                            val lat: Int,
                            @Json(name = "lineGroup")
                            val lineGroup: List<LineGroup>,
                            @Json(name = "lineModeGroups")
                            val lineModeGroups: List<LineModeGroup>,
                            @Json(name = "lines")
                            val lines: List<Line>,
                            @Json(name = "lon")
                            val lon: Int,
                            @Json(name = "modes")
                            val modes: List<String>,
                            @Json(name = "naptanId")
                            val naptanId: String,
                            @Json(name = "naptanMode")
                            val naptanMode: String,
                            @Json(name = "placeType")
                            val placeType: String,
                            @Json(name = "platformName")
                            val platformName: String,
                            @Json(name = "smsCode")
                            val smsCode: String,
                            @Json(name = "stationNaptan")
                            val stationNaptan: String,
                            @Json(name = "status")
                            val status: Boolean,
                            @Json(name = "stopLetter")
                            val stopLetter: String,
                            @Json(name = "stopType")
                            val stopType: String,
                            @Json(name = "url")
                            val url: String
                        ) {
                            data class Line(
                                @Json(name = "crowding")
                                val crowding: Crowding,
                                @Json(name = "fullName")
                                val fullName: String,
                                @Json(name = "id")
                                val id: String,
                                @Json(name = "name")
                                val name: String,
                                @Json(name = "routeType")
                                val routeType: String,
                                @Json(name = "status")
                                val status: String,
                                @Json(name = "type")
                                val type: String,
                                @Json(name = "uri")
                                val uri: String
                            ) {
                                data class Crowding(
                                    @Json(name = "passengerFlows")
                                    val passengerFlows: List<PassengerFlow>,
                                    @Json(name = "trainLoadings")
                                    val trainLoadings: List<TrainLoading>
                                ) {
                                    data class TrainLoading(
                                        @Json(name = "direction")
                                        val direction: String,
                                        @Json(name = "line")
                                        val line: String,
                                        @Json(name = "lineDirection")
                                        val lineDirection: String,
                                        @Json(name = "naptanTo")
                                        val naptanTo: String,
                                        @Json(name = "platformDirection")
                                        val platformDirection: String,
                                        @Json(name = "timeSlice")
                                        val timeSlice: String,
                                        @Json(name = "value")
                                        val value: Int
                                    )

                                    data class PassengerFlow(
                                        @Json(name = "timeSlice")
                                        val timeSlice: String,
                                        @Json(name = "value")
                                        val value: Int
                                    )
                                }
                            }

                            data class AdditionalProperty(
                                @Json(name = "category")
                                val category: String,
                                @Json(name = "key")
                                val key: String,
                                @Json(name = "modified")
                                val modified: String,
                                @Json(name = "sourceSystemKey")
                                val sourceSystemKey: String,
                                @Json(name = "value")
                                val value: String
                            )

                            data class LineGroup(
                                @Json(name = "lineIdentifier")
                                val lineIdentifier: List<String>,
                                @Json(name = "naptanIdReference")
                                val naptanIdReference: String,
                                @Json(name = "stationAtcoCode")
                                val stationAtcoCode: String
                            )

                            data class Children(
                                @Json(name = "additionalProperties")
                                val additionalProperties: List<AdditionalProperty>,
                                @Json(name = "children")
                                val children: List<Any>,
                                @Json(name = "childrenUrls")
                                val childrenUrls: List<String>,
                                @Json(name = "commonName")
                                val commonName: String,
                                @Json(name = "distance")
                                val distance: Int,
                                @Json(name = "id")
                                val id: String,
                                @Json(name = "lat")
                                val lat: Int,
                                @Json(name = "lon")
                                val lon: Int,
                                @Json(name = "placeType")
                                val placeType: String,
                                @Json(name = "url")
                                val url: String
                            ) {
                                data class AdditionalProperty(
                                    @Json(name = "category")
                                    val category: String,
                                    @Json(name = "key")
                                    val key: String,
                                    @Json(name = "modified")
                                    val modified: String,
                                    @Json(name = "sourceSystemKey")
                                    val sourceSystemKey: String,
                                    @Json(name = "value")
                                    val value: String
                                )
                            }

                            data class LineModeGroup(
                                @Json(name = "lineIdentifier")
                                val lineIdentifier: List<String>,
                                @Json(name = "modeName")
                                val modeName: String
                            )
                        }
                    }
                }
            }
        }
    }

    data class JourneyVector(
        @Json(name = "from")
        val from: String,
        @Json(name = "to")
        val to: String,
        @Json(name = "uri")
        val uri: String,
        @Json(name = "via")
        val via: String
    )

    data class Journey(
        @Json(name = "arrivalDateTime")
        val arrivalDateTime: String,
        @Json(name = "duration")
        val duration: Int,
        @Json(name = "fare")
        val fare: Fare,
        @Json(name = "legs")
        val legs: List<Leg>,
        @Json(name = "startDateTime")
        val startDateTime: String
    ) {
        data class Fare(
            @Json(name = "caveats")
            val caveats: List<Caveat>,
            @Json(name = "fares")
            val fares: List<Fare>,
            @Json(name = "totalCost")
            val totalCost: Int
        ) {
            data class Caveat(
                @Json(name = "text")
                val text: String,
                @Json(name = "type")
                val type: String
            )

            data class Fare(
                @Json(name = "chargeLevel")
                val chargeLevel: String,
                @Json(name = "chargeProfileName")
                val chargeProfileName: String,
                @Json(name = "cost")
                val cost: Int,
                @Json(name = "highZone")
                val highZone: Int,
                @Json(name = "isHopperFare")
                val isHopperFare: Boolean,
                @Json(name = "lowZone")
                val lowZone: Int,
                @Json(name = "offPeak")
                val offPeak: Int,
                @Json(name = "peak")
                val peak: Int,
                @Json(name = "taps")
                val taps: List<Tap>
            ) {
                data class Tap(
                    @Json(name = "atcoCode")
                    val atcoCode: String,
                    @Json(name = "tapDetails")
                    val tapDetails: TapDetails
                ) {
                    data class TapDetails(
                        @Json(name = "busRouteId")
                        val busRouteId: String,
                        @Json(name = "hostDeviceType")
                        val hostDeviceType: String,
                        @Json(name = "modeType")
                        val modeType: String,
                        @Json(name = "nationalLocationCode")
                        val nationalLocationCode: Int,
                        @Json(name = "tapTimestamp")
                        val tapTimestamp: String,
                        @Json(name = "validationType")
                        val validationType: String
                    )
                }
            }
        }

        data class Leg(
            @Json(name = "arrivalPoint")
            val arrivalPoint: ArrivalPoint,
            @Json(name = "arrivalTime")
            val arrivalTime: String,
            @Json(name = "departurePoint")
            val departurePoint: DeparturePoint,
            @Json(name = "departureTime")
            val departureTime: String,
            @Json(name = "disruptions")
            val disruptions: List<Disruption>,
            @Json(name = "distance")
            val distance: Int,
            @Json(name = "duration")
            val duration: Int,
            @Json(name = "hasFixedLocations")
            val hasFixedLocations: Boolean,
            @Json(name = "instruction")
            val instruction: Instruction,
            @Json(name = "isDisrupted")
            val isDisrupted: Boolean,
            @Json(name = "mode")
            val mode: Mode,
            @Json(name = "obstacles")
            val obstacles: List<Obstacle>,
            @Json(name = "path")
            val path: Path,
            @Json(name = "plannedWorks")
            val plannedWorks: List<PlannedWork>,
            @Json(name = "routeOptions")
            val routeOptions: List<RouteOption>,
            @Json(name = "speed")
            val speed: String
        ) {
            data class Instruction(
                @Json(name = "detailed")
                val detailed: String,
                @Json(name = "steps")
                val steps: List<Step>,
                @Json(name = "summary")
                val summary: String
            ) {
                data class Step(
                    @Json(name = "cumulativeDistance")
                    val cumulativeDistance: Int,
                    @Json(name = "cumulativeTravelTime")
                    val cumulativeTravelTime: Int,
                    @Json(name = "description")
                    val description: String,
                    @Json(name = "descriptionHeading")
                    val descriptionHeading: String,
                    @Json(name = "distance")
                    val distance: Int,
                    @Json(name = "latitude")
                    val latitude: Int,
                    @Json(name = "longitude")
                    val longitude: Int,
                    @Json(name = "pathAttribute")
                    val pathAttribute: PathAttribute,
                    @Json(name = "skyDirection")
                    val skyDirection: Int,
                    @Json(name = "skyDirectionDescription")
                    val skyDirectionDescription: String,
                    @Json(name = "streetName")
                    val streetName: String,
                    @Json(name = "trackType")
                    val trackType: String,
                    @Json(name = "turnDirection")
                    val turnDirection: String
                ) {
                    data class PathAttribute(
                        @Json(name = "name")
                        val name: String,
                        @Json(name = "value")
                        val value: String
                    )
                }
            }

            data class PlannedWork(
                @Json(name = "createdDateTime")
                val createdDateTime: String,
                @Json(name = "description")
                val description: String,
                @Json(name = "id")
                val id: String,
                @Json(name = "lastUpdateDateTime")
                val lastUpdateDateTime: String
            )

            data class Disruption(
                @Json(name = "additionalInfo")
                val additionalInfo: String,
                @Json(name = "affectedRoutes")
                val affectedRoutes: List<AffectedRoute>,
                @Json(name = "affectedStops")
                val affectedStops: List<AffectedStop>,
                @Json(name = "category")
                val category: String,
                @Json(name = "categoryDescription")
                val categoryDescription: String,
                @Json(name = "closureText")
                val closureText: String,
                @Json(name = "created")
                val created: String,
                @Json(name = "description")
                val description: String,
                @Json(name = "lastUpdate")
                val lastUpdate: String,
                @Json(name = "summary")
                val summary: String,
                @Json(name = "type")
                val type: String
            ) {
                data class AffectedStop(
                    @Json(name = "accessibilitySummary")
                    val accessibilitySummary: String,
                    @Json(name = "additionalProperties")
                    val additionalProperties: List<AdditionalProperty>,
                    @Json(name = "children")
                    val children: List<Children>,
                    @Json(name = "childrenUrls")
                    val childrenUrls: List<String>,
                    @Json(name = "commonName")
                    val commonName: String,
                    @Json(name = "distance")
                    val distance: Int,
                    @Json(name = "fullName")
                    val fullName: String,
                    @Json(name = "hubNaptanCode")
                    val hubNaptanCode: String,
                    @Json(name = "icsCode")
                    val icsCode: String,
                    @Json(name = "id")
                    val id: String,
                    @Json(name = "indicator")
                    val indicator: String,
                    @Json(name = "lat")
                    val lat: Int,
                    @Json(name = "lineGroup")
                    val lineGroup: List<LineGroup>,
                    @Json(name = "lineModeGroups")
                    val lineModeGroups: List<LineModeGroup>,
                    @Json(name = "lines")
                    val lines: List<Line>,
                    @Json(name = "lon")
                    val lon: Int,
                    @Json(name = "modes")
                    val modes: List<String>,
                    @Json(name = "naptanId")
                    val naptanId: String,
                    @Json(name = "naptanMode")
                    val naptanMode: String,
                    @Json(name = "placeType")
                    val placeType: String,
                    @Json(name = "platformName")
                    val platformName: String,
                    @Json(name = "smsCode")
                    val smsCode: String,
                    @Json(name = "stationNaptan")
                    val stationNaptan: String,
                    @Json(name = "status")
                    val status: Boolean,
                    @Json(name = "stopLetter")
                    val stopLetter: String,
                    @Json(name = "stopType")
                    val stopType: String,
                    @Json(name = "url")
                    val url: String
                ) {
                    data class Line(
                        @Json(name = "crowding")
                        val crowding: Crowding,
                        @Json(name = "fullName")
                        val fullName: String,
                        @Json(name = "id")
                        val id: String,
                        @Json(name = "name")
                        val name: String,
                        @Json(name = "routeType")
                        val routeType: String,
                        @Json(name = "status")
                        val status: String,
                        @Json(name = "type")
                        val type: String,
                        @Json(name = "uri")
                        val uri: String
                    ) {
                        data class Crowding(
                            @Json(name = "passengerFlows")
                            val passengerFlows: List<PassengerFlow>,
                            @Json(name = "trainLoadings")
                            val trainLoadings: List<TrainLoading>
                        ) {
                            data class TrainLoading(
                                @Json(name = "direction")
                                val direction: String,
                                @Json(name = "line")
                                val line: String,
                                @Json(name = "lineDirection")
                                val lineDirection: String,
                                @Json(name = "naptanTo")
                                val naptanTo: String,
                                @Json(name = "platformDirection")
                                val platformDirection: String,
                                @Json(name = "timeSlice")
                                val timeSlice: String,
                                @Json(name = "value")
                                val value: Int
                            )

                            data class PassengerFlow(
                                @Json(name = "timeSlice")
                                val timeSlice: String,
                                @Json(name = "value")
                                val value: Int
                            )
                        }
                    }

                    data class LineGroup(
                        @Json(name = "lineIdentifier")
                        val lineIdentifier: List<String>,
                        @Json(name = "naptanIdReference")
                        val naptanIdReference: String,
                        @Json(name = "stationAtcoCode")
                        val stationAtcoCode: String
                    )

                    data class AdditionalProperty(
                        @Json(name = "category")
                        val category: String,
                        @Json(name = "key")
                        val key: String,
                        @Json(name = "modified")
                        val modified: String,
                        @Json(name = "sourceSystemKey")
                        val sourceSystemKey: String,
                        @Json(name = "value")
                        val value: String
                    )

                    data class LineModeGroup(
                        @Json(name = "lineIdentifier")
                        val lineIdentifier: List<String>,
                        @Json(name = "modeName")
                        val modeName: String
                    )

                    data class Children(
                        @Json(name = "additionalProperties")
                        val additionalProperties: List<AdditionalProperty>,
                        @Json(name = "children")
                        val children: List<Any>,
                        @Json(name = "childrenUrls")
                        val childrenUrls: List<String>,
                        @Json(name = "commonName")
                        val commonName: String,
                        @Json(name = "distance")
                        val distance: Int,
                        @Json(name = "id")
                        val id: String,
                        @Json(name = "lat")
                        val lat: Int,
                        @Json(name = "lon")
                        val lon: Int,
                        @Json(name = "placeType")
                        val placeType: String,
                        @Json(name = "url")
                        val url: String
                    ) {
                        data class AdditionalProperty(
                            @Json(name = "category")
                            val category: String,
                            @Json(name = "key")
                            val key: String,
                            @Json(name = "modified")
                            val modified: String,
                            @Json(name = "sourceSystemKey")
                            val sourceSystemKey: String,
                            @Json(name = "value")
                            val value: String
                        )
                    }
                }

                data class AffectedRoute(
                    @Json(name = "destinationName")
                    val destinationName: String,
                    @Json(name = "direction")
                    val direction: String,
                    @Json(name = "id")
                    val id: String,
                    @Json(name = "lineId")
                    val lineId: String,
                    @Json(name = "lineString")
                    val lineString: String,
                    @Json(name = "name")
                    val name: String,
                    @Json(name = "originationName")
                    val originationName: String,
                    @Json(name = "routeCode")
                    val routeCode: String,
                    @Json(name = "routeSectionNaptanEntrySequence")
                    val routeSectionNaptanEntrySequence: List<RouteSectionNaptanEntrySequence>,
                    @Json(name = "validFrom")
                    val validFrom: String,
                    @Json(name = "validTo")
                    val validTo: String
                ) {
                    data class RouteSectionNaptanEntrySequence(
                        @Json(name = "ordinal")
                        val ordinal: Int,
                        @Json(name = "stopPoint")
                        val stopPoint: StopPoint
                    ) {
                        data class StopPoint(
                            @Json(name = "accessibilitySummary")
                            val accessibilitySummary: String,
                            @Json(name = "additionalProperties")
                            val additionalProperties: List<AdditionalProperty>,
                            @Json(name = "children")
                            val children: List<Children>,
                            @Json(name = "childrenUrls")
                            val childrenUrls: List<String>,
                            @Json(name = "commonName")
                            val commonName: String,
                            @Json(name = "distance")
                            val distance: Int,
                            @Json(name = "fullName")
                            val fullName: String,
                            @Json(name = "hubNaptanCode")
                            val hubNaptanCode: String,
                            @Json(name = "icsCode")
                            val icsCode: String,
                            @Json(name = "id")
                            val id: String,
                            @Json(name = "indicator")
                            val indicator: String,
                            @Json(name = "lat")
                            val lat: Int,
                            @Json(name = "lineGroup")
                            val lineGroup: List<LineGroup>,
                            @Json(name = "lineModeGroups")
                            val lineModeGroups: List<LineModeGroup>,
                            @Json(name = "lines")
                            val lines: List<Line>,
                            @Json(name = "lon")
                            val lon: Int,
                            @Json(name = "modes")
                            val modes: List<String>,
                            @Json(name = "naptanId")
                            val naptanId: String,
                            @Json(name = "naptanMode")
                            val naptanMode: String,
                            @Json(name = "placeType")
                            val placeType: String,
                            @Json(name = "platformName")
                            val platformName: String,
                            @Json(name = "smsCode")
                            val smsCode: String,
                            @Json(name = "stationNaptan")
                            val stationNaptan: String,
                            @Json(name = "status")
                            val status: Boolean,
                            @Json(name = "stopLetter")
                            val stopLetter: String,
                            @Json(name = "stopType")
                            val stopType: String,
                            @Json(name = "url")
                            val url: String
                        ) {
                            data class Line(
                                @Json(name = "crowding")
                                val crowding: Crowding,
                                @Json(name = "fullName")
                                val fullName: String,
                                @Json(name = "id")
                                val id: String,
                                @Json(name = "name")
                                val name: String,
                                @Json(name = "routeType")
                                val routeType: String,
                                @Json(name = "status")
                                val status: String,
                                @Json(name = "type")
                                val type: String,
                                @Json(name = "uri")
                                val uri: String
                            ) {
                                data class Crowding(
                                    @Json(name = "passengerFlows")
                                    val passengerFlows: List<PassengerFlow>,
                                    @Json(name = "trainLoadings")
                                    val trainLoadings: List<TrainLoading>
                                ) {
                                    data class TrainLoading(
                                        @Json(name = "direction")
                                        val direction: String,
                                        @Json(name = "line")
                                        val line: String,
                                        @Json(name = "lineDirection")
                                        val lineDirection: String,
                                        @Json(name = "naptanTo")
                                        val naptanTo: String,
                                        @Json(name = "platformDirection")
                                        val platformDirection: String,
                                        @Json(name = "timeSlice")
                                        val timeSlice: String,
                                        @Json(name = "value")
                                        val value: Int
                                    )

                                    data class PassengerFlow(
                                        @Json(name = "timeSlice")
                                        val timeSlice: String,
                                        @Json(name = "value")
                                        val value: Int
                                    )
                                }
                            }

                            data class LineGroup(
                                @Json(name = "lineIdentifier")
                                val lineIdentifier: List<String>,
                                @Json(name = "naptanIdReference")
                                val naptanIdReference: String,
                                @Json(name = "stationAtcoCode")
                                val stationAtcoCode: String
                            )

                            data class AdditionalProperty(
                                @Json(name = "category")
                                val category: String,
                                @Json(name = "key")
                                val key: String,
                                @Json(name = "modified")
                                val modified: String,
                                @Json(name = "sourceSystemKey")
                                val sourceSystemKey: String,
                                @Json(name = "value")
                                val value: String
                            )

                            data class LineModeGroup(
                                @Json(name = "lineIdentifier")
                                val lineIdentifier: List<String>,
                                @Json(name = "modeName")
                                val modeName: String
                            )

                            data class Children(
                                @Json(name = "additionalProperties")
                                val additionalProperties: List<AdditionalProperty>,
                                @Json(name = "children")
                                val children: List<Any>,
                                @Json(name = "childrenUrls")
                                val childrenUrls: List<String>,
                                @Json(name = "commonName")
                                val commonName: String,
                                @Json(name = "distance")
                                val distance: Int,
                                @Json(name = "id")
                                val id: String,
                                @Json(name = "lat")
                                val lat: Int,
                                @Json(name = "lon")
                                val lon: Int,
                                @Json(name = "placeType")
                                val placeType: String,
                                @Json(name = "url")
                                val url: String
                            ) {
                                data class AdditionalProperty(
                                    @Json(name = "category")
                                    val category: String,
                                    @Json(name = "key")
                                    val key: String,
                                    @Json(name = "modified")
                                    val modified: String,
                                    @Json(name = "sourceSystemKey")
                                    val sourceSystemKey: String,
                                    @Json(name = "value")
                                    val value: String
                                )
                            }
                        }
                    }
                }
            }

            data class DeparturePoint(
                @Json(name = "lat")
                val lat: Int,
                @Json(name = "lon")
                val lon: Int
            )

            data class ArrivalPoint(
                @Json(name = "lat")
                val lat: Int,
                @Json(name = "lon")
                val lon: Int
            )

            data class RouteOption(
                @Json(name = "directions")
                val directions: List<String>,
                @Json(name = "id")
                val id: String,
                @Json(name = "lineIdentifier")
                val lineIdentifier: LineIdentifier,
                @Json(name = "name")
                val name: String
            ) {
                data class LineIdentifier(
                    @Json(name = "crowding")
                    val crowding: Crowding,
                    @Json(name = "fullName")
                    val fullName: String,
                    @Json(name = "id")
                    val id: String,
                    @Json(name = "name")
                    val name: String,
                    @Json(name = "routeType")
                    val routeType: String,
                    @Json(name = "status")
                    val status: String,
                    @Json(name = "type")
                    val type: String,
                    @Json(name = "uri")
                    val uri: String
                ) {
                    data class Crowding(
                        @Json(name = "passengerFlows")
                        val passengerFlows: List<PassengerFlow>,
                        @Json(name = "trainLoadings")
                        val trainLoadings: List<TrainLoading>
                    ) {
                        data class TrainLoading(
                            @Json(name = "direction")
                            val direction: String,
                            @Json(name = "line")
                            val line: String,
                            @Json(name = "lineDirection")
                            val lineDirection: String,
                            @Json(name = "naptanTo")
                            val naptanTo: String,
                            @Json(name = "platformDirection")
                            val platformDirection: String,
                            @Json(name = "timeSlice")
                            val timeSlice: String,
                            @Json(name = "value")
                            val value: Int
                        )

                        data class PassengerFlow(
                            @Json(name = "timeSlice")
                            val timeSlice: String,
                            @Json(name = "value")
                            val value: Int
                        )
                    }
                }
            }

            data class Path(
                @Json(name = "elevation")
                val elevation: List<Elevation>,
                @Json(name = "lineString")
                val lineString: String,
                @Json(name = "stopPoints")
                val stopPoints: List<StopPoint>
            ) {
                data class StopPoint(
                    @Json(name = "crowding")
                    val crowding: Crowding,
                    @Json(name = "fullName")
                    val fullName: String,
                    @Json(name = "id")
                    val id: String,
                    @Json(name = "name")
                    val name: String,
                    @Json(name = "routeType")
                    val routeType: String,
                    @Json(name = "status")
                    val status: String,
                    @Json(name = "type")
                    val type: String,
                    @Json(name = "uri")
                    val uri: String
                ) {
                    data class Crowding(
                        @Json(name = "passengerFlows")
                        val passengerFlows: List<PassengerFlow>,
                        @Json(name = "trainLoadings")
                        val trainLoadings: List<TrainLoading>
                    ) {
                        data class TrainLoading(
                            @Json(name = "direction")
                            val direction: String,
                            @Json(name = "line")
                            val line: String,
                            @Json(name = "lineDirection")
                            val lineDirection: String,
                            @Json(name = "naptanTo")
                            val naptanTo: String,
                            @Json(name = "platformDirection")
                            val platformDirection: String,
                            @Json(name = "timeSlice")
                            val timeSlice: String,
                            @Json(name = "value")
                            val value: Int
                        )

                        data class PassengerFlow(
                            @Json(name = "timeSlice")
                            val timeSlice: String,
                            @Json(name = "value")
                            val value: Int
                        )
                    }
                }

                data class Elevation(
                    @Json(name = "distance")
                    val distance: Int,
                    @Json(name = "endLat")
                    val endLat: Int,
                    @Json(name = "endLon")
                    val endLon: Int,
                    @Json(name = "gradient")
                    val gradient: Int,
                    @Json(name = "heightFromPreviousPoint")
                    val heightFromPreviousPoint: Int,
                    @Json(name = "startLat")
                    val startLat: Int,
                    @Json(name = "startLon")
                    val startLon: Int
                )
            }

            data class Obstacle(
                @Json(name = "incline")
                val incline: String,
                @Json(name = "position")
                val position: String,
                @Json(name = "stopId")
                val stopId: Int,
                @Json(name = "type")
                val type: String
            )

            data class Mode(
                @Json(name = "crowding")
                val crowding: Crowding,
                @Json(name = "fullName")
                val fullName: String,
                @Json(name = "id")
                val id: String,
                @Json(name = "name")
                val name: String,
                @Json(name = "routeType")
                val routeType: String,
                @Json(name = "status")
                val status: String,
                @Json(name = "type")
                val type: String,
                @Json(name = "uri")
                val uri: String
            ) {
                data class Crowding(
                    @Json(name = "passengerFlows")
                    val passengerFlows: List<PassengerFlow>,
                    @Json(name = "trainLoadings")
                    val trainLoadings: List<TrainLoading>
                ) {
                    data class TrainLoading(
                        @Json(name = "direction")
                        val direction: String,
                        @Json(name = "line")
                        val line: String,
                        @Json(name = "lineDirection")
                        val lineDirection: String,
                        @Json(name = "naptanTo")
                        val naptanTo: String,
                        @Json(name = "platformDirection")
                        val platformDirection: String,
                        @Json(name = "timeSlice")
                        val timeSlice: String,
                        @Json(name = "value")
                        val value: Int
                    )

                    data class PassengerFlow(
                        @Json(name = "timeSlice")
                        val timeSlice: String,
                        @Json(name = "value")
                        val value: Int
                    )
                }
            }
        }
    }
}