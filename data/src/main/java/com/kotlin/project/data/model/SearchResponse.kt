package com.kotlin.project.data.model

data class SearchResponse(
    val page: Int = 0,
    val results: ArrayList<ResultsData> = arrayListOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)
