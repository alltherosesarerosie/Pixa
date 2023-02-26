package com.geektech.pixa

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PixaService {

    private var retrofit = Retrofit.Builder().baseUrl("https://pixabay.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    val api = retrofit.create(PixaApi::class.java)
}