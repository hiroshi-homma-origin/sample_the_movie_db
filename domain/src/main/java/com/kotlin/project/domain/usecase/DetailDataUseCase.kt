package com.kotlin.project.domain.usecase

import com.kotlin.project.data.BuildConfig
import com.kotlin.project.data.model.response.DetailResponse
import com.kotlin.project.data.model.result.TheMovieDBResult
import com.kotlin.project.data.repository.DetailDataRepository
import javax.inject.Inject

interface DetailDataUseCase {
    suspend fun detailData(
        path: Int,
        apiKey: String,
        language: String? = BuildConfig.LANGUAGE
    ): TheMovieDBResult<DetailResponse>
}

class DetailDataUseCaseImpl @Inject constructor(
    private val detailDataRepository: DetailDataRepository
) : DetailDataUseCase {
    override suspend fun detailData(
        path: Int,
        apiKey: String,
        language: String?
    ): TheMovieDBResult<DetailResponse> =
        runCatching { detailDataRepository.detailData(path, apiKey, language) }
            .fold(
                onSuccess = { return TheMovieDBResult.Success(it) },
                onFailure = { return TheMovieDBResult.Failure(Throwable("TheMovieDB error")) }
            )
}
