package com.core.network.remote.response.episode

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)