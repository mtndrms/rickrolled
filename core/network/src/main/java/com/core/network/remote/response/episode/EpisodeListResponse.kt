package com.core.network.remote.response.episode

data class EpisodeListResponse(
    val info: Info,
    val results: List<Episode>
)