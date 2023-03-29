package com.example.rickrolled.data.remote.response.episode

data class EpisodeListResponse(
    val info: Info,
    val results: List<Episode>
)