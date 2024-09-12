package com.ryu.apifetch.api

import com.ryu.apifetch.api.services.AnimeServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://wajik-anime-api.vercel.app/otakudesu/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val animeService: AnimeServices by lazy {
        retrofit.create(AnimeServices::class.java)
    }
}