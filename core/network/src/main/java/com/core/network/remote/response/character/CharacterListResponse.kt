package com.core.network.remote.response.character

data class CharacterListResponse(
    val info: Info,
    val results: List<Character>
)