package com.kotlin.project.data.model

data class SearchResultsTvData(
    val backdropPath: String?,
    val firstAirDate: String,
    val genreIds: ArrayList<Int>,
    val id: Int,
    val name: String,
    val originCountry: ArrayList<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String?,
    val voteAverage: Float,
    val voteCount: Int
)
