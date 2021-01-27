package com.kotlin.project.data.model.response

import com.kotlin.project.data.model.SearchResultsTvData

data class SearchTvResponse(
    val page: Int = 0,
    val results: ArrayList<SearchResultsTvData> = arrayListOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)
