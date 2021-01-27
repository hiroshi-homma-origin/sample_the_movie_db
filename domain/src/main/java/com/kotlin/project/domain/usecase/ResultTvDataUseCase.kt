package com.kotlin.project.domain.usecase

import com.kotlin.project.data.entities.ResultTvData
import com.kotlin.project.data.repository.ResultTvDataRepository
import javax.inject.Inject

interface ResultTvDataUseCase {
    suspend fun get20Data(): ResultTvData
    suspend fun getTv(): ResultTvData
    suspend fun deleteAll()
    suspend fun insert(resultTvData: ResultTvData)
    suspend fun delete(resultTvData: ResultTvData)
}

class ResultTvDataUseCaseImpl @Inject constructor(
    private val repository: ResultTvDataRepository
) : ResultTvDataUseCase {
    override suspend fun get20Data(): ResultTvData = repository.get20Data()
    override suspend fun getTv(): ResultTvData = repository.getTv()
    override suspend fun deleteAll() = repository.deleteAll()
    override suspend fun insert(resultTvData: ResultTvData) = repository.insert(resultTvData)
    override suspend fun delete(resultTvData: ResultTvData) = repository.delete(resultTvData)
}
