package com.kotlin.project.domain.usecase

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.kotlin.project.data.BuildConfig
import com.kotlin.project.data.model.failure.Failure
import com.kotlin.project.data.model.response.DetailResponse
import com.kotlin.project.data.repository.DetailDataRepository
import javax.inject.Inject

interface DetailDataUseCase {
    suspend fun detailData(
        path: Int,
        apiKey: String,
        language: String? = BuildConfig.LANGUAGE
    ): Result<DetailResponse, Failure>
}

class DetailDataUseCaseImpl @Inject constructor(
    private val detailDataRepository: DetailDataRepository
) : DetailDataUseCase {
    override suspend fun detailData(
        path: Int,
        apiKey: String,
        language: String?
    ): Result<DetailResponse, Failure> = Ok(detailDataRepository.detailData(path, apiKey, language))
}
