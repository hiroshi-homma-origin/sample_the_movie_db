package com.kotlin.project.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ResultTvData(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "backdropPath") val backdropPath: String?,
    @ColumnInfo(name = "firstAirDate") val firstAirDate: String,
    @ColumnInfo(name = "genreIds") val genreIds: ArrayList<Int>,
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "originCountry") val originCountry: ArrayList<String>,
    @ColumnInfo(name = "originalLanguage") val originalLanguage: String,
    @ColumnInfo(name = "originalName") val originalName: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "popularity") val popularity: Float,
    @ColumnInfo(name = "posterPath") val posterPath: String?,
    @ColumnInfo(name = "voteAverage") val voteAverage: Float,
    @ColumnInfo(name = "voteCount") val voteCount: Int
)
