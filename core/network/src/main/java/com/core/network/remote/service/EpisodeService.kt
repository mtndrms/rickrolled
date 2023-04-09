package com.core.network.remote.service

import com.core.network.remote.response.episode.EpisodeListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodeService {
    @GET("episode")
    suspend fun getAllEpisodes(@Query("page") page: Int): EpisodeListResponse
}