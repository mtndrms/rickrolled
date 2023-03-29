package com.example.rickrolled.data.remote.service

import com.example.rickrolled.data.remote.response.character.CharacterListResponse
import com.example.rickrolled.data.remote.response.episode.EpisodeListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodeService {
    @GET("episode")
    suspend fun getAllEpisodes(@Query("page") page: Int): EpisodeListResponse
}