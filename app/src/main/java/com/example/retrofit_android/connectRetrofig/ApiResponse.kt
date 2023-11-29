package com.example.retrofit_android.connectRetrofig

import android.util.Log
import com.example.retrofit_android.model.ListBookingStatus
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ApiResponse {

    fun responseServer(call: Call<ListBookingStatus>) {

        return call.enqueue(object : Callback<ListBookingStatus> {
            override fun onResponse(call: Call<ListBookingStatus>, response: Response<ListBookingStatus>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    val gson = Gson()
                    val dataJson = gson.toJson(data)
                    Log.i("dataJson", dataJson)
                    // Handle the response data here
                } else {
                    // Handle the error
                }
            }

            override fun onFailure(call: Call<ListBookingStatus>, t: Throwable) {
                // Handle the failure
            }
        })

    }


}