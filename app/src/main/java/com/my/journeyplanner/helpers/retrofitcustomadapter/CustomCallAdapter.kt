package com.my.journeyplanner.helpers.retrofitcustomadapter

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class CustomCallAdapter<R>(private val responseType: Type) : CallAdapter<R, Any> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): Any = CustomCall(call)
}