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
    ): Converter<ResponseBody, JourneyPlannerResult>? {
        Log.d(TAG, "responseBodyConverter() params: ${type}, ${annotations.size}, $retrofit")

        when (type) {
            JourneyPlannerResult.FromAndToDisambiguationOptions::class.java,
            JourneyPlannerResult.FromDisambiguationOptions::class.java,
            JourneyPlannerResult.ToDisambiguationOptions::class.java,
            JourneyPlannerResult.Itinerary::class.java,
            JourneyPlannerResult.NotIdentified::class.java -> true
            else -> {
                Log.d(TAG, "JourneyPlannerResult type: $type not matched")
                return null
            }
        }

        return Converter { body ->
            //                TODO("Handle other result types")
            val jsonBody = JSONObject(body.string())
            val fromDisambiguationOptionsCount =
                jsonBody.getJSONObject("fromLocationDisambiguation")
                    .getJSONArray("disambiguationOptions").length()
            val toDisambiguationOptionsCount =
                jsonBody.getJSONObject("toLocationDisambiguation")
                    .getJSONArray("disambiguationOptions").length()

            when {
                fromDisambiguationOptionsCount > 0 && toDisambiguationOptionsCount > 0 -> {
                    Log.d(TAG, "FromAndToDisambiguationOptions type")
                    val fromAndToDisambiguationOptionsConverter =
                        retrofit.nextResponseBodyConverter<JourneyPlannerResult.FromAndToDisambiguationOptions>(
                            this,
                            type,
                            annotations
                        )

                    fromAndToDisambiguationOptionsConverter.convert(body)
                }
                else -> {
                    Log.d(TAG, "No converter match")
                    null
                }
            }
        }
    }

    companion object {
        val TAG = this::class.java.simpleName as String
    }
}