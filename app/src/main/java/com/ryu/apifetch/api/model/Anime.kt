package com.ryu.apifetch.api.model

data class AnimeOngoing (
    val judul: String,
    val slug: String,
    val poster: String,
    val episodeTerbaru: String,
    val hariRilis: String
)

data class AnimeCompleted(
    val judul: String,
    val slug: String,
    val poster: String,
    val jumlahEpisode: String,
    val rating: String
)