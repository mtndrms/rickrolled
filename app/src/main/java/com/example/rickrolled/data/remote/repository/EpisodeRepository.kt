package com.example.rickrolled.data.remote.repository

import com.example.rickrolled.data.entity.Episode
import com.example.rickrolled.data.remote.service.EpisodeService

class EpisodeRepository(private val service: EpisodeService) {
    suspend fun getPage(page: Int): List<Episode> {
        val episodes: MutableList<Episode> = mutableListOf()

        service.getAllEpisodes(page).results.forEach {
            episodes.add(
                Episode(
                    it.id, it.name, it.episode, it.air_date
                )
            )
        }
        return episodes
    }
}