package com.kotlin.project.data.model.response

import com.kotlin.project.data.model.BelongsToCollection
import com.kotlin.project.data.model.Genres
import com.kotlin.project.data.model.ProductionCompanies
import com.kotlin.project.data.model.ProductionCountries
import com.kotlin.project.data.model.SpokenLanguages

data class DetailResponse(
    val adult: Boolean,
    val backdropPath: String?,
    val belongsToCollection: BelongsToCollection,
    val budget: Int,
    val genres: ArrayList<Genres>,
    val homepage: String,
    val id: Int,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String?,
    val productionCompanies: ArrayList<ProductionCompanies>,
    val productionCountries: ArrayList<ProductionCountries>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: ArrayList<SpokenLanguages>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Float,
    val voteCount: Int
)
