package com.kotlin.project.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kotlin.project.data.model.search.SearchMovieData

@Entity
data class MovieData(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "backdropPath") val backdropPath: String?,
    @ColumnInfo(name = "genreIds") val genreIds: ArrayList<Int>?,
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "originalLanguage") val originalLanguage: String,
    @ColumnInfo(name = "originalTitle") val originalTitle: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "popularity") val popularity: Float,
    @ColumnInfo(name = "posterPath") val posterPath: String?,
    @ColumnInfo(name = "releaseDate") val releaseDate: String?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "video") val video: Boolean,
    @ColumnInfo(name = "voteAverage") val voteAverage: Float,
    @ColumnInfo(name = "voteCount") val voteCount: Int
)

fun MovieData.transform(): SearchMovieData {
    return SearchMovieData(
        adult = adult,
        backdropPath = backdropPath ?: "",
        genreIds = genreIds ?: arrayListOf(),
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath ?: "",
        releaseDate = releaseDate ?: "",
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}
