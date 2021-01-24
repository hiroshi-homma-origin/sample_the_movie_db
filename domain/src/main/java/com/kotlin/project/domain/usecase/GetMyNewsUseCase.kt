package com.kotlin.project.domain.usecase

import com.kotlin.project.data.BuildConfig
import com.kotlin.project.data.model.SearchResponse
import com.kotlin.project.data.repository.GetMovieListRepository
import javax.inject.Inject

interface GetMovieListUseCase {
    suspend fun getMovieList(
        apiKey: String,
        searchWord: String,
        page: Int? = 1,
        language: String? = BuildConfig.LANGUAGE
    ): SearchResponse
}

class GetMovieListUseCaseImpl @Inject constructor(
    private val getMasterListRepository: GetMovieListRepository
) : GetMovieListUseCase {
    override suspend fun getMovieList(
        apiKey: String,
        searchWord: String,
        page: Int?,
        language: String?
    ): SearchResponse =
        runCatching { getMasterListRepository.getMovieList(apiKey, searchWord, page, language) }
            .fold(
                onSuccess = { return it },
                onFailure = { return SearchResponse() }
            )
}
