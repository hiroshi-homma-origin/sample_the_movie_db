package com.kotlin.project.data.model.response

import com.kotlin.project.data.model.SearchResultsData

data class SearchResponse(
    val page: Int = 0,
    val results: ArrayList<SearchResultsData> = arrayListOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)
