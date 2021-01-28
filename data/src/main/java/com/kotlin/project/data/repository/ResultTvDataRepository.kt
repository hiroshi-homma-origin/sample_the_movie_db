package com.kotlin.project.data.repository

import com.kotlin.project.data.dao.ResultTvDataDao
import com.kotlin.project.data.entities.ResultTvData
import javax.inject.Inject

interface ResultTvDataRepository {
    suspend fun get20Data(): List<ResultTvData>
    suspend fun getTv(): List<ResultTvData>
    suspend fun findId(id: Int): ResultTvData?
    suspend fun deleteAll()
    suspend fun insert(resultTvData: ResultTvData)
    suspend fun delete(resultTvData: ResultTvData)
}

class ResultTvDataRepositoryImpl @Inject constructor(
    private val dao: ResultTvDataDao
) : ResultTvDataRepository {

    override suspend fun get20Data(): List<ResultTvData> = dao.get20Data()

    override suspend fun getTv(): List<ResultTvData> = dao.getTv()

    override suspend fun findId(id: Int): ResultTvData? = dao.findId(id)

    override suspend fun deleteAll() = dao.deleteAll()

    override suspend fun insert(resultTvData: ResultTvData) = dao.insert(resultTvData)

    override suspend fun delete(resultTvData: ResultTvData) = dao.delete(resultTvData)
}
