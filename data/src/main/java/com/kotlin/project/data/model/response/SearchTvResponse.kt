package com.kotlin.project.data.model.response

import com.kotlin.project.data.model.search.SearchTvData

data class SearchTvResponse(
    val page: Int = 0,
    val results: ArrayList<SearchTvData> = arrayListOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)
