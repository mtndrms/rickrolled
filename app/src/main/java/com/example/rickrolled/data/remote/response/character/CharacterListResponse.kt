package com.example.rickrolled.data.remote.response.character

data class CharacterListResponse(
    val info: Info,
    val results: List<Character>
)