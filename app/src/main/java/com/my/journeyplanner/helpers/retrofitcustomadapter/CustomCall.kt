package com.my.journeyplanner.helpers.retrofitcustomadapter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class CustomCall<R>(private val call: Call<R>) {
    fun execute(responseHandler: (R?, Throwable?) -> Unit) {
        try {
            handleResponse(call.execute(), responseHandler)
        } catch (t: IOException) {
            responseHandler(null, t)
        }
    }

    fun enqueue(responseHandler: (R?, Throwable?) -> Unit) {
        val callback = object : Callback<R> {
            override fun onFailure(call: Call<R>?, t: Throwable?) =
                responseHandler(null, t)

            override fun onResponse(call: Call<R>?, r: Response<R>?) =
                handleResponse(r, responseHandler)
        }
        call.enqueue(callback)
    }

    private fun handleResponse(response: Response<R>?, handler: (R?, Throwable?) -> Unit) {
        println("response: $response, isSuccessful: ${response?.isSuccessful}")
        println("errorBody(): ${response?.errorBody()?.charStream()?.readText()}")
        if (response?.isSuccessful == true) {
            handler(response.body(), null)
        } else {
            when (response?.code()) {
//                TODO("Handle response.errorBody(). Type mismatch. Required: R?, Found: ResponseBody?")
                // https://android.jlelse.eu/building-your-own-retrofit-call-adapter-b198169bab69
//                https://medium.com/@Dalvin/how-to-parse-http-error-body-using-retrofit-bb348f67fc54
                300 -> handler(response.body(), null)
                in 400..511 -> handler(null, HttpException(response as Response<*>))
                else -> handler(response?.body(), null)
            }
        }
    }

    fun cancel() {
        call.cancel()
    }
}