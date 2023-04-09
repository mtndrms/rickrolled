package com.core.network.remote.service

import com.core.network.remote.response.character.CharacterListResponse
import com.core.network.remote.response.character.Character
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {
    @GET("character")
    suspend fun getAllCharacters(@Query("page") page: Int): CharacterListResponse

    @GET("character/:characterIDs")
    suspend fun getMultipleCharacters(@Query("page") page: Int): CharacterListResponse

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Character
}