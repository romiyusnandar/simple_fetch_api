package com.ryu.apifetch.api.model

data class AnimeResponse(
    val statusCode: Int,
    val statusMessage: String,
    val message: String,
    val data: AnimeData
)

data class AnimeData(
    val onGoing: List<AnimeOngoing>,
    val completed: List<AnimeCompleted>
)
