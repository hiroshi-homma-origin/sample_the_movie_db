package com.kotlin.project.data.repository

import com.kotlin.project.data.BuildConfig
import com.kotlin.project.data.api.TheMovieDBApi
import com.kotlin.project.data.model.SearchResponse
import javax.inject.Inject

interface GetMovieListRepository {
    suspend fun getMovieList(
        apiKey: String,
        searchWord: String,
        page: Int? = 1,
        language: String? = BuildConfig.LANGUAGE
    ): SearchResponse
}

internal class GetMovieListRepositoryImpl @Inject constructor(
    private val theMovieDBApi: TheMovieDBApi
) : GetMovieListRepository {

    override suspend fun getMovieList(
        apiKey: String,
        searchWord: String,
        page: Int?,
        language: String?
    ): SearchResponse = theMovieDBApi.getMovieList(apiKey, searchWord, page, language)
}
