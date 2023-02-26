package com.geektech.pixa

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {
    @GET("api/")
    fun getImages(
        @Query("key") key: String = "33919494-d07c5da9294c97d470057abc2",
        @Query("q") pictureWord: String,
        @Query("per_page") perPage:Int =3,
        @Query("page") page:Int,
    ): Call<PixaModel>
}