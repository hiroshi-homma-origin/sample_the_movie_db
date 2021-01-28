package com.kotlin.project.domain.usecase

import com.kotlin.project.data.entities.TvData
import com.kotlin.project.data.repository.ResultTvDataRepository
import javax.inject.Inject

interface ResultTvDataUseCase {
    suspend fun getTv(): List<TvData>
    suspend fun findId(id: Int): TvData?
    suspend fun deleteAll()
    suspend fun insert(tvData: TvData)
    suspend fun delete(tvData: TvData)
}

class ResultTvDataUseCaseImpl @Inject constructor(
    private val repository: ResultTvDataRepository
) : ResultTvDataUseCase {
    override suspend fun getTv(): List<TvData> = repository.getTv()
    override suspend fun findId(id: Int): TvData? = repository.findId(id)
    override suspend fun deleteAll() = repository.deleteAll()
    override suspend fun insert(tvData: TvData) = repository.insert(tvData)
    override suspend fun delete(tvData: TvData) = repository.delete(tvData)
}
