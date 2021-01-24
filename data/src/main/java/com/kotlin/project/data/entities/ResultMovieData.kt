package com.kotlin.project.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ResultMovieData(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "backdropPath") val backdropPath: String,
    @ColumnInfo(name = "genreIds") val genreIds: ArrayList<Int>,
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "originalLanguage") val originalLanguage: String,
    @ColumnInfo(name = "originalTitle") val originalTitle: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "popularity") val popularity: Float,
    @ColumnInfo(name = "posterPath") val posterPath: String,
    @ColumnInfo(name = "releaseDate") val releaseDate: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "video") val video: Boolean,
    @ColumnInfo(name = "voteAverage") val voteAverage: Float,
    @ColumnInfo(name = "voteCount") val voteCount: Int
)
