package com.kotlin.project.data.model.search

import com.kotlin.project.data.entities.MovieData

data class SearchMovieData(
    val adult: Boolean,
    val backdropPath: String?,
    val genreIds: ArrayList<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Float,
    val voteCount: Int
)

fun SearchMovieData.transform(): MovieData {
    return MovieData(
        uid = 0,
        adult = adult,
        backdropPath = backdropPath ?: "",
        genreIds = genreIds,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath ?: "",
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}
