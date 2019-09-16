package com.my.journeyplanner.framework

import com.my.core.domain.JourneyPlannerResult
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class JourneyPlannerResultConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, JourneyPlannerResult> {
        val itineraryConverterFactory =
            retrofit.nextResponseBodyConverter<JourneyPlannerResult.Itinerary>(
                this,
                JourneyPlannerResult.Itinerary::class.java,
                annotations
            )
        val fromAndToDisambiguationOptionsConverterFactory =
            retrofit.nextResponseBodyConverter<JourneyPlannerResult.FromToDisambiguationOptions>(
                this,
                JourneyPlannerResult.FromToDisambiguationOptions::class.java,
                annotations
            )

        return Converter { body ->
            val bodyString = body.string()
            val jsonBody = JSONObject(bodyString)
            val isItineraryResult = jsonBody.isResultType(ITINERARY_RESULT)
            val isDisambiguationResult = jsonBody.isResultType(DISAMBIGUATION_RESULT)
            val bodyClone = ResponseBody.create(body.contentType(), bodyString)

            when {
                isItineraryResult -> itineraryConverterFactory.convert(bodyClone)
                isDisambiguationResult -> fromAndToDisambiguationOptionsConverterFactory.convert(
                    bodyClone
                )
                else -> throw IllegalStateException()
            }
        }
    }
}