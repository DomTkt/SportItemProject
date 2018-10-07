package com.example.dom.firstkotlinproject.interfaces

interface IApiResponse<T> {
    fun onSuccess(list: List<T>)
    fun onError(t: Throwable)
}