package com.example.dynamicadscreen.apiservice

import com.example.dynamicadscreen.model.OfferResponse
import retrofit2.http.GET

interface OfferApiService {
    @GET("/offer")
    suspend fun getOffer(): OfferResponse
}