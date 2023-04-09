package com.core.network.utils

import com.core.network.entity.Character
import com.core.network.entity.Episode
import com.core.network.remote.response.episode.Episode as EpisodeResponse
import com.core.network.remote.response.character.Character as CharacterResponse

fun List<CharacterResponse>.toCharacterList(): List<Character> {
    return this.map {
        Character(it.id, it.name, it.image, 1, 1)
    }
}

fun List<EpisodeResponse>.toEpisodeList(): List<Episode> {
    return this.map {
        Episode(it.id, it.name, it.episode, it.air_date)
    }
}