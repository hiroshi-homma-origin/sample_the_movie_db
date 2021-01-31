package com.kotlin.project.data.repository

import com.kotlin.project.data.dao.TvDataDao
import com.kotlin.project.data.entities.MovieData
import com.kotlin.project.data.entities.TvData
import javax.inject.Inject

interface ResultTvDataRepository {
    suspend fun getTv(): List<TvData>
    suspend fun findId(id: Int): TvData?
    suspend fun insert(tvData: TvData)
    suspend fun updateIsFavorite(id: Int, isUpdate: Boolean)
    suspend fun delete(tvData: TvData)
    suspend fun deleteAll()
}

class ResultTvDataRepositoryImpl @Inject constructor(
    private val dao: TvDataDao
) : ResultTvDataRepository {

    override suspend fun getTv(): List<TvData> = dao.getTv()
    override suspend fun findId(id: Int): TvData? = dao.findId(id)
    override suspend fun insert(tvData: TvData) = dao.insert(tvData)
    override suspend fun updateIsFavorite(id: Int, isUpdate: Boolean) = dao.updateIsFavorite(id, isUpdate)
    override suspend fun delete(tvData: TvData) = dao.delete(tvData)
    override suspend fun deleteAll() = dao.deleteAll()
}
