package com.my.api

import com.my.core.domain.ICancellable
import com.my.core.domain.ICustomCallback
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
        toLocation: String,
        callback: ICustomCallback<JourneyPlannerResultDomainModel>
    ): ICancellable {
        val call =
            JourneyPlannerApiService.createApiService().getJourneyResults(fromLocation, toLocation)
        call.enqueue(object : Callback<JourneyPlannerResult> {

            override fun onFailure(call: Call<JourneyPlannerResult>, t: Throwable) {
                callback.onError(t)
                logger.error(if (t is IOException) "Network failure" else "Conversion failure", t)
            }

            override fun onResponse(
                call: Call<JourneyPlannerResult>,
                response: Response<JourneyPlannerResult>
            ) {
//                TODO("Remove later")
                logger.info("response.raw().request(): ${response.raw().request()}")
                if (response.isSuccessful) {
                    val responseBodyString = response.body().toString()

                    callback.onSuccess(
                        when {
                            responseBodyString.contains(ITINERARY_RESULT) -> transformItineraryTO(
                                response
                            )
//                        responseBodyString.contains(DISAMBIGUATION_OPTIONS) -> {
////                            TODO("as above")
//                            null
//                        }
                            else -> throw IllegalStateException()
                        }
                    )
                } else {
                    logger.info("responseCode: ${response.code()}")
                }
            }
        })
        return object : ICancellable {
            override fun cancel() {
                call.cancel()
            }
        }
    }
}