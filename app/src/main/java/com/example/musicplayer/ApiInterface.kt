package com.example.musicplayer

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiInterface {

    @Headers("x-rapidapi-key: 033e2587d8msh038ed95af614a08p1bb877jsne2e2543d8006" ,
            "x-rapidapi-host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query: String) : Call<MyData>

}