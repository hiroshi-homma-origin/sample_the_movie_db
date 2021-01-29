package com.kotlin.project.domain.usecase

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.kotlin.project.data.BuildConfig
import com.kotlin.project.data.model.failure.Failure
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
    ): Result<SearchResponse, Failure>

    suspend fun searchTvList(
        apiKey: String,
        searchWord: String,
        page: Int? = 1,
        language: String? = BuildConfig.LANGUAGE
    ): Result<SearchTvResponse, Failure>
}

class SearchListUseCaseImpl @Inject constructor(
    private val searchListRepository: SearchListRepository
) : SearchListUseCase {
    override suspend fun searchList(
        apiKey: String,
        searchWord: String,
        page: Int?,
        language: String?
    ): Result<SearchResponse, Failure> = Ok(searchListRepository.searchList(apiKey, searchWord, page, language))

    override suspend fun searchTvList(
        apiKey: String,
        searchWord: String,
        page: Int?,
        language: String?
    ): Result<SearchTvResponse, Failure> = Ok(searchListRepository.searchTvList(apiKey, searchWord, page, language))
}
