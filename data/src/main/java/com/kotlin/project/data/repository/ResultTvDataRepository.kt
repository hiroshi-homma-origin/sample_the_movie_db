package com.kotlin.project.data.repository

import com.kotlin.project.data.dao.ResultTvDataDao
import com.kotlin.project.data.entities.ResultTvData
import javax.inject.Inject

interface ResultTvDataRepository {
    suspend fun getTv(): ResultTvData
    suspend fun deleteAll()
    suspend fun insert(resultTvData: ResultTvData)
    suspend fun delete(resultTvData: ResultTvData)
}

class ResultTvDataRepositoryImpl @Inject constructor(
    private val dao: ResultTvDataDao
) : ResultTvDataRepository {

    override suspend fun getTv(): ResultTvData = dao.getTv()

    override suspend fun deleteAll() = dao.deleteAll()

    override suspend fun insert(resultTvData: ResultTvData) = dao.insert(resultTvData)

    override suspend fun delete(resultTvData: ResultTvData) = dao.delete(resultTvData)
}
