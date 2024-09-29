package com.example.dynamicadscreen

import com.example.dynamicadscreen.apiservice.OfferApiService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/") // Use localhost IP for Android emulator
            .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON deserialization
            .build()
    }

    val api: OfferApiService by lazy {
        retrofit.create(OfferApiService::class.java)
    }
}
