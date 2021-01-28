package com.kotlin.project.data.model.search

import com.kotlin.project.data.entities.TvData

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

fun SearchResultsTvData.transform(): TvData {
    return TvData(
        uid = 0,
        backdropPath = backdropPath ?: "",
        firstAirDate = firstAirDate,
        genreIds = genreIds,
        id = id,
        name = name,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        originalName = originalName,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath ?: "",
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}
