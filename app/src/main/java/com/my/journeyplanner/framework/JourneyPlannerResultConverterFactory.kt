package com.my.journeyplanner.framework

import android.util.Log
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
        Log.d(TAG, "responseBodyConverter() params: ${type}, ${annotations.size}, $retrofit")

        val itineraryConverterFactory =
            retrofit.nextResponseBodyConverter<JourneyPlannerResult.Itinerary>(
                this,
                JourneyPlannerResult.Itinerary::class.java,
                annotations
            )
        val fromAndToDisambiguationOptionsConverterFactory =
            retrofit.nextResponseBodyConverter<JourneyPlannerResult.FromAndToDisambiguationOptions>(
                this,
                JourneyPlannerResult.FromAndToDisambiguationOptions::class.java,
                annotations
            )

        val notIdentifiedConverterFactory =
            retrofit.nextResponseBodyConverter<JourneyPlannerResult.NotIdentified>(
                this,
                JourneyPlannerResult.NotIdentified::class.java,
                annotations
            )

        val fromDisambiguationOptionsConverterFactory =
            retrofit.nextResponseBodyConverter<JourneyPlannerResult.FromDisambiguationOptions>(
                this,
                JourneyPlannerResult.FromDisambiguationOptions::class.java,
                annotations
            )

        val toDisambiguationOptionsConverterFactory =
            retrofit.nextResponseBodyConverter<JourneyPlannerResult.ToDisambiguationOptions>(
                this,
                JourneyPlannerResult.ToDisambiguationOptions::class.java,
                annotations
            )

        return Converter { body ->
            val bodyString = body.string()
            val jsonBody = JSONObject(bodyString)
            val hasFromLocationDisambiguation = jsonBody.has(FROM_LOCATION_DISAMBIGUATION)
            val hasToLocationDisambiguation = jsonBody.has(TO_LOCATION_DISAMBIGUATION)
            val hasFromLocationDisambiguationMatchStatus =
                hasLocationDisambiguationKey(
                    hasFromLocationDisambiguation,
                    jsonBody,
                    FROM_LOCATION_DISAMBIGUATION,
                    MATCH_STATUS
                )
            val hasToLocationDisambiguationMatchStatus =
                hasLocationDisambiguationKey(
                    hasToLocationDisambiguation,
                    jsonBody,
                    TO_LOCATION_DISAMBIGUATION,
                    MATCH_STATUS
                )
            val isNotIdentified =
                hasFromLocationDisambiguationMatchStatus && hasToLocationDisambiguationMatchStatus
            val isItineraryResult = isItineraryResult(jsonBody)
            val hasFromLocationDisambiguationOptions =
                hasLocationDisambiguationKey(
                    hasFromLocationDisambiguation,
                    jsonBody,
                    FROM_LOCATION_DISAMBIGUATION,
                    DISAMBIGUATION_OPTIONS
                )
            val hasToLocationDisambiguationOptions =
                hasLocationDisambiguationKey(
                    hasToLocationDisambiguation,
                    jsonBody,
                    TO_LOCATION_DISAMBIGUATION,
                    DISAMBIGUATION_OPTIONS
                )
            val isFromAndToLocationDisambiguationOptions =
                hasFromLocationDisambiguationOptions && hasToLocationDisambiguationOptions
            val isFromDisambiguationOptions =
                hasFromLocationDisambiguationOptions && !hasToLocationDisambiguationOptions
            val isToDisambiguationOptions =
                !hasFromLocationDisambiguationOptions && hasToLocationDisambiguationOptions
            val bodyClone = ResponseBody.create(body.contentType(), bodyString)

            when {
                isNotIdentified -> notIdentifiedConverterFactory.convert(bodyClone)
                isItineraryResult -> itineraryConverterFactory.convert(bodyClone)
                isFromAndToLocationDisambiguationOptions -> fromAndToDisambiguationOptionsConverterFactory.convert(
                    bodyClone
                )
                isFromDisambiguationOptions -> fromDisambiguationOptionsConverterFactory.convert(
                    bodyClone
                )
                isToDisambiguationOptions -> toDisambiguationOptionsConverterFactory.convert(
                    bodyClone
                )
                else -> throw IllegalStateException()
            }
        }
    }

    companion object {
        val TAG = this::class.java.simpleName as String
    }
}