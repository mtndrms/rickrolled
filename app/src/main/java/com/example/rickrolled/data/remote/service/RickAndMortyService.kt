package com.example.rickrolled.data.remote.service

import com.example.rickrolled.data.remote.response.CharacterList
import com.example.rickrolled.data.remote.response.Character
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("character")
    suspend fun getAllCharacters(@Query("page") page: Int): CharacterList

    @GET("character/{id}")
    suspend fun getOneCharacter(@Path("id") id: Int): Character

}