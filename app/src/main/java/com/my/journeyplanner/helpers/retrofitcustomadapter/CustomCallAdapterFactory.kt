package com.my.journeyplanner.helpers.retrofitcustomadapter

import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class CustomCallAdapterFactory private constructor() : CallAdapter.Factory() {

    override fun get(returnType: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): CallAdapter<*, *>? {
        return returnType?.let {
            return try {
                val enclosingType = (it as ParameterizedType)

                if (enclosingType.rawType != CustomCall::class.java) {
                    null
                } else {
                    CustomCallAdapter<Any>(enclosingType.actualTypeArguments[0])
                }
            } catch (ex: ClassCastException) {
                null
            }
        }
    }

    companion object {
        @JvmStatic
        fun create() = CustomCallAdapterFactory()
    }
}