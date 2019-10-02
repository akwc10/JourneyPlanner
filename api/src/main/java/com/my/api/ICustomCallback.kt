package com.my.api

interface ICustomCallback<T> {
    fun onSuccess(result: T)

    fun onError(t: Throwable)
}