package com.my.core.domain

interface ICustomCallback<T> {
    fun onSuccess(result: T)

    fun onError(t: Throwable)
}