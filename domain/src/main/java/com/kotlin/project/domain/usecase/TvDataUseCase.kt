package com.kotlin.project.domain.usecase

import com.kotlin.project.data.model.search.SearchTvData
import com.kotlin.project.data.repository.ResultTvDataRepository
import javax.inject.Inject

interface ResultTvDataUseCase {
    suspend fun getTv(): List<SearchTvData>
    suspend fun getFavoriteTv(): List<SearchTvData>
    suspend fun findId(id: Int): SearchTvData?
    suspend fun insert(tvData: SearchTvData)
    suspend fun updateIsFavorite(id: Int, isUpdate: Boolean)
    suspend fun delete(tvData: SearchTvData)
    suspend fun deleteAll()
}

class ResultTvDataUseCaseImpl @Inject constructor(
    private val repository: ResultTvDataRepository
) : ResultTvDataUseCase {
    override suspend fun getTv(): List<SearchTvData> = repository.getTv()

    override suspend fun getFavoriteTv(): List<SearchTvData> = repository.getFavoriteTv()

    override suspend fun findId(id: Int): SearchTvData? = repository.findId(id)

    override suspend fun insert(tvData: SearchTvData) = repository.insert(tvData)

    override suspend fun updateIsFavorite(id: Int, isUpdate: Boolean) =
        repository.updateIsFavorite(id, isUpdate)

    override suspend fun delete(tvData: SearchTvData) = repository.delete(tvData)

    override suspend fun deleteAll() = repository.deleteAll()
}
