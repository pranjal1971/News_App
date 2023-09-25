package com.deva.newsapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api_Interface {

    @GET("top-headlines")
    fun getnewsData(@Query("country") country: String,
                    @Query("apiKey") apiKey: String):Call<newsData>
}