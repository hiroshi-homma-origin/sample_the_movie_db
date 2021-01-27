package com.kotlin.project.domain.usecase

import com.kotlin.project.data.BuildConfig
import com.kotlin.project.data.model.SearchResponse
import com.kotlin.project.data.model.TheMovieDBResult
import com.kotlin.project.data.repository.GetMovieListRepository
import javax.inject.Inject

interface SearchListUseCase {
    suspend fun searchList(
        apiKey: String,
        searchWord: String,
        page: Int? = 1,
        language: String? = BuildConfig.LANGUAGE
    ): TheMovieDBResult<SearchResponse>
}

class SearchListUseCaseImpl @Inject constructor(
    private val getMasterListRepository: GetMovieListRepository
) : SearchListUseCase {
    override suspend fun searchList(
        apiKey: String,
        searchWord: String,
        page: Int?,
        language: String?
    ): TheMovieDBResult<SearchResponse> =
        runCatching { getMasterListRepository.getMovieList(apiKey, searchWord, page, language) }
            .fold(
                onSuccess = { return TheMovieDBResult.Success(it) },
                onFailure = { return TheMovieDBResult.Failure(Throwable("TheMovieDB error")) }
            )
}
