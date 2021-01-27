package com.kotlin.project.data.repository

import com.kotlin.project.data.BuildConfig
import com.kotlin.project.data.api.TheMovieDBApi
import com.kotlin.project.data.model.response.SearchResponse
import com.kotlin.project.data.model.response.SearchTvResponse
import javax.inject.Inject

interface SearchListRepository {
    suspend fun searchList(
        apiKey: String,
        searchWord: String,
        page: Int? = 1,
        language: String? = BuildConfig.LANGUAGE
    ): SearchResponse

    suspend fun searchTvList(
        apiKey: String,
        searchWord: String,
        page: Int? = 1,
        language: String? = BuildConfig.LANGUAGE
    ): SearchTvResponse
}

internal class SearchListRepositoryImpl @Inject constructor(
    private val theMovieDBApi: TheMovieDBApi
) : SearchListRepository {

    override suspend fun searchList(
        apiKey: String,
        searchWord: String,
        page: Int?,
        language: String?
    ): SearchResponse = theMovieDBApi.searchList(apiKey, searchWord, page, language)

    override suspend fun searchTvList(
        apiKey: String,
        searchWord: String,
        page: Int?,
        language: String?
    ): SearchTvResponse = theMovieDBApi.searchTvList(apiKey, searchWord, page, language)
}
