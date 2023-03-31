package com.example.rickrolled.data.remote

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val aRequest: Request = chain.request()
        val aResponse = chain.proceed(aRequest)
        when (aResponse.code) {
            200 -> {
                Log.i("200", aRequest.url.toString())
            }

            400 -> {
                // Show Bad Request Error Message
            }

            401 -> {
                // Show UnauthorizedError Message
            }

            403 -> {
                // Show Forbidden Message
            }

            404 -> {
                // Show NotFound Message
            }
            // ... and so on
        }
        return aResponse
    }
}