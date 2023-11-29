package com.example.retrofit_android.connectRetrofig

import android.os.Build
import android.text.TextUtils
import com.example.retrofit_android.config.Config.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                .header(
                    "Authorization",
                    "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2RlbW9hcGkubHVtcHN1bS5pbi50aCIsImF1ZCI6Imh0dHBzOi8vZGVtb2FwaS5sdW1wc3VtLmluLnRoIiwianRpIjoiYWE4MjU1OWQtNjlkOS00NGI3LThlMmQtOTcxZmEyZDE4NGJkIiwic3ViIjoiMTc0MjEiLCJuYW1lIjoiTW9iaWxlIiwicm9sZSI6WyJ1c2VyIl0sIm5iZiI6MTcwMDY0NTM2NiwiZXhwIjoxNzAwNzMxNzY2LCJpYXQiOjE3MDA2NDUzNjZ9.QDoUQ3M4rTW-J8XKw3cxh9tBjOHLCuCHGR6Dxsn6RRSrUodL6m274yWCKxZYeICCQp6_f1JCu_n86WZg9TpTxg"
                )
                .header("Content-Type", "application/json")
                .header("accept", "*/*")
                .header("os_version", getAndroidVersion())
                .header("os_type", "android")
                .header("lang", "th")
                .header("device", getDeviceName())
                .header("lumpsum_unique_id", "c92f22edd2a60030")
                .header("lumpsum_token_id", "")
                .header("user_token", "4ce813bc-13c1-4b22-9bd4-e66a14b0a618")
                .header("app_version", "2.2.5")
                // Add other headers if needed
                .method(originalRequest.method(), originalRequest.body())

            val request = requestBuilder.build()
            chain.proceed(request)
        }
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private fun getAndroidVersion(): String {
        val release = Build.VERSION.RELEASE
        val sdkVersion = Build.VERSION.SDK_INT
        return "Android SDK: $sdkVersion ($release)"
    }

    private fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer)) ({
            capitalize(model)
        }).toString() else capitalize(manufacturer) + " " + model
    }

    private fun capitalize(str: String): String? {
        if (TextUtils.isEmpty(str)) {
            return str
        }
        val arr = str.toCharArray()
        var capitalizeNext = true
        val phrase = StringBuilder()
        for (c in arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(c.uppercaseChar())
                capitalizeNext = false
                continue
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true
            }
            phrase.append(c)
        }
        return phrase.toString()
    }
}
