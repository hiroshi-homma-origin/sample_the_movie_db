package com.kotlin.project.data.model.response

import com.kotlin.project.data.model.search.SearchMovieData

data class SearchResponse(
    val page: Int = 0,
    val results: ArrayList<SearchMovieData> = arrayListOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)
