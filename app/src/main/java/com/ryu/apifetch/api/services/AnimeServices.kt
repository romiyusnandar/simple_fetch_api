package com.ryu.apifetch.api.services

import com.ryu.apifetch.api.model.AnimeResponse
import retrofit2.http.GET
import retrofit2.Call

interface AnimeServices {

    @GET("home")
    fun getAnime(): Call<AnimeResponse>

}