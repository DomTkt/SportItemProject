package com.example.dom.firstkotlinproject.managers

import com.example.dom.firstkotlinproject.interfaces.IApiResponse
import com.example.dom.firstkotlinproject.services.ISportItemLocationAPIServices
import models.SportItemLocation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object DataManager {

    fun create(): ISportItemLocationAPIServices {
        val url = "https://api.myjson.com"
        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        return retrofit.create(ISportItemLocationAPIServices::class.java)
    }

    fun getSport(callback : IApiResponse<SportItemLocation>) : Call<List<SportItemLocation>> {
        val listSportItem = create().listSportItem()
        listSportItem.enqueue(object : Callback<List<SportItemLocation>> {
            override fun onResponse(call: Call<List<SportItemLocation>>, response: Response<List<SportItemLocation>>) {
                callback.onSuccess(response.body()!!)
            }
            override fun onFailure(call: Call<List<SportItemLocation>>, t: Throwable) {
                callback.onError(t)
            }
        })
        return listSportItem
    }
}