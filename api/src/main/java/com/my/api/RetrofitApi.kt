package com.my.api

import com.my.core.domain.JourneyPlannerResultDomainModel
import mu.KotlinLogging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

private val logger = KotlinLogging.logger {}

class RetrofitApi : IRetrofitApi {
    var mResponse: JourneyPlannerResult? = null
    var mJourneyPlannerResultDomainModel: JourneyPlannerResultDomainModel? = null

    override fun getJourneyResults(
        fromLocation: String,
        toLocation: String
    ): Call<JourneyPlannerResult> =
        JourneyPlannerApiService.createApiService().getJourneyResults(fromLocation, toLocation)

    override fun enqueue(call: Call<JourneyPlannerResult>) {
        call.enqueue(object : Callback<JourneyPlannerResult> {

            override fun onFailure(
                call: Call<JourneyPlannerResult>,
                throwable: Throwable
            ) {
                logger.error(
                    TAG,
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

                    logger.debug { "headers(): ${response.headers()}" }
                    logger.debug { "raw(): ${response.raw()}" }
                    logger.debug { "body(): $responseBodyString" }

                    mResponse = response.body()
                    mJourneyPlannerResultDomainModel = when {
                        responseBodyString.contains(ITINERARY_RESULT) -> parseItineraryTO(response)

                        responseBodyString.contains(DISAMBIGUATION_OPTIONS) -> {
//                            TODO("as above")
                            null
                        }
                        else -> throw IllegalStateException()
                    }
                    logger.debug(
                        TAG,
                        "journeyPlannerResultDomainModel: $mJourneyPlannerResultDomainModel"
                    )
                } else {
                    logger.debug(TAG, "responseCode: ${response.code()}")
                }


            }
        })
    }

    override fun cancelAsyncCall(call: Call<JourneyPlannerResult>?) {
        call?.cancel()
    }

    companion object {
        val TAG = this::class.java.simpleName
    }
}