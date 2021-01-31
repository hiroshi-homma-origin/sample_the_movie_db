package com.kotlin.project.domain.usecase

import com.kotlin.project.data.entities.TvData
import com.kotlin.project.data.repository.ResultTvDataRepository
import javax.inject.Inject

interface ResultTvDataUseCase {
    suspend fun getTv(): List<TvData>
    suspend fun findId(id: Int): TvData?
    suspend fun insert(tvData: TvData)
    suspend fun updateIsFavorite(id: Int, isUpdate: Boolean)
    suspend fun delete(tvData: TvData)
    suspend fun deleteAll()
}

class ResultTvDataUseCaseImpl @Inject constructor(
    private val repository: ResultTvDataRepository
) : ResultTvDataUseCase {
    override suspend fun getTv(): List<TvData> = repository.getTv()
    override suspend fun findId(id: Int): TvData? = repository.findId(id)
    override suspend fun insert(tvData: TvData) = repository.insert(tvData)
    override suspend fun updateIsFavorite(id: Int, isUpdate: Boolean) = repository.updateIsFavorite(id, isUpdate)
    override suspend fun delete(tvData: TvData) = repository.delete(tvData)
    override suspend fun deleteAll() = repository.deleteAll()
}
