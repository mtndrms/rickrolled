package com.core.network.repository

import com.core.network.entity.Episode
import com.core.network.remote.service.EpisodeService
import com.core.network.utils.toEpisodeList

class EpisodeRepository(private val service: EpisodeService) {
    suspend fun getPage(page: Int): List<Episode> {
        return service.getAllEpisodes(page).results.toEpisodeList()
    }
}