package com.example.dynamicadscreen.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dynamicadscreen.RetrofitInstance
import com.example.dynamicadscreen.model.OfferResponse
import kotlinx.coroutines.launch

class OfferViewModel : ViewModel() {
    private val _offer = mutableStateOf<OfferResponse?>(null)
    val offer: State<OfferResponse?> get() = _offer

    private var lastOfferId: Int? = null // Track the last offerId to detect changes

    init {
        fetchOffer()
    }

    fun fetchOffer() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getOffer()

                // Log the response to check if the API is returning anything
                Log.d("API_RESPONSE", "Offer received: $response")

                if (response.offerId != lastOfferId) {
                    // If the offerId has changed, update the offer and show the popup
                    _offer.value = response
                    lastOfferId = response.offerId

                    // Log the updated offer data
                    Log.d("API_RESPONSE", "New offer ID: ${response.offerId}")
                } else {
                    Log.d("API_RESPONSE", "No new offer received")
                }

            } catch (e: Exception) {
                // Log the error if something goes wrong
                Log.e("API_ERROR", "Error fetching offer: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}
