package com.my.api

import com.my.core.domain.JourneyPlannerResultDomainModel
import mu.KotlinLogging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

private val logger = KotlinLogging.logger {}

class JourneyPlannerApi : IJourneyPlannerApi {
    override fun getJourneyResults(
        fromLocation: String,
        toLocation: String
    ): Call<JourneyPlannerResult> =
        JourneyPlannerApiService.createApiService().getJourneyResults(fromLocation, toLocation)

    override fun enqueue(
        call: Call<JourneyPlannerResult>,
        journeyPlannerResultDomainModels: MutableList<JourneyPlannerResultDomainModel?>
    ) {
        call.enqueue(object : Callback<JourneyPlannerResult> {

            override fun onFailure(
                call: Call<JourneyPlannerResult>,
                throwable: Throwable
            ) {
                logger.error(
                    if (throwable is IOException) "Network failure" else "Conversion failure",
                    throwable
                )
            }

            override fun onResponse(
                call: Call<JourneyPlannerResult>,
                response: Response<JourneyPlannerResult>
            ) {
                if (response.isSuccessful) {
                    val responseBodyString = response.body().toString()

                    val journeyPlannerResultDomainModel = when {
                        responseBodyString.contains(ITINERARY_RESULT) -> transformItineraryTO(
                            response
                        )
                        responseBodyString.contains(DISAMBIGUATION_OPTIONS) -> {
//                            TODO("as above")
                            null
                        }
                        else -> throw IllegalStateException()
                    }
                    journeyPlannerResultDomainModels.clear()
                    journeyPlannerResultDomainModels.add(journeyPlannerResultDomainModel)
//                    println(journeyPlannerResultDomainModel)
                } else {
                    logger.info("responseCode: ${response.code()}")
                }
            }
        })
    }

    override fun cancelAsyncCall(call: Call<JourneyPlannerResult>?) {
        call?.cancel()
    }
}