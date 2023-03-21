package com.example.rickrolled.data.remote

import com.example.rickrolled.utils.Constants
import com.example.rickrolled.data.remote.service.RickAndMortyService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private var instance: RickAndMortyService? = null

        @Synchronized
        fun getInstance(): RickAndMortyService {
            if (instance == null)
                instance = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_API_URL)
                    .build()
                    .create(RickAndMortyService::class.java)
            return instance as RickAndMortyService
        }
    }
}