package com.example.dom.firstkotlinproject.services

import com.example.dom.firstkotlinproject.models.SportItemLocation
import retrofit2.Call
import retrofit2.http.GET

interface ISportItemLocationAPIServices {

    @GET("/bins/cyzg0")
    fun listSportItem(): Call<List<SportItemLocation>>
}