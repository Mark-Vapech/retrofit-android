package com.example.retrofit_android.connectRetrofig

import com.example.retrofit_android.model.ListBookingStatus
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("/bookingapi/api/BookingLFP/ListBooking/S")
    fun getData(): Call<ListBookingStatus>

}