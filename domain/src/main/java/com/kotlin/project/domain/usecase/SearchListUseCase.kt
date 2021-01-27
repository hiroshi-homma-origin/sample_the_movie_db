package com.kotlin.project.domain.usecase

import com.kotlin.project.data.BuildConfig
import com.kotlin.project.data.model.TheMovieDBResult
import com.kotlin.project.data.model.response.SearchResponse
import com.kotlin.project.data.model.response.SearchTvResponse
import com.kotlin.project.data.repository.SearchListRepository
import javax.inject.Inject

interface SearchListUseCase {
    suspend fun searchList(
        apiKey: String,
        searchWord: String,
        page: Int? = 1,
        language: String? = BuildConfig.LANGUAGE
    ): TheMovieDBResult<SearchResponse>

    suspend fun searchTvList(
        apiKey: String,
        searchWord: String,
        page: Int? = 1,
        language: String? = BuildConfig.LANGUAGE
    ): TheMovieDBResult<SearchTvResponse>
}

class SearchListUseCaseImpl @Inject constructor(
    private val searchListRepository: SearchListRepository
) : SearchListUseCase {
    override suspend fun searchList(
        apiKey: String,
        searchWord: String,
        page: Int?,
        language: String?
    ): TheMovieDBResult<SearchResponse> =
        runCatching { searchListRepository.searchList(apiKey, searchWord, page, language) }
            .fold(
                onSuccess = { return TheMovieDBResult.Success(it) },
                onFailure = { return TheMovieDBResult.Failure(Throwable("TheMovieDB error")) }
            )

    override suspend fun searchTvList(
        apiKey: String,
        searchWord: String,
        page: Int?,
        language: String?
    ): TheMovieDBResult<SearchTvResponse> =
        runCatching { searchListRepository.searchTvList(apiKey, searchWord, page, language) }
            .fold(
                onSuccess = { return TheMovieDBResult.Success(it) },
                onFailure = { return TheMovieDBResult.Failure(Throwable("TheMovieDB error")) }
            )
}
