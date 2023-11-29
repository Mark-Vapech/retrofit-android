package com.example.retrofit_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofit_android.connectRetrofig.APIService
import com.example.retrofit_android.connectRetrofig.ApiResponse
import com.example.retrofit_android.connectRetrofig.RetrofitClient
import com.example.retrofit_android.model.ListBookingStatus
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setAPI()

    }

    private fun setAPI() {
        val apiService = RetrofitClient.retrofit.create(APIService::class.java)
        val call = apiService.getData()

        call.enqueue(object : Callback<ListBookingStatus> {
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