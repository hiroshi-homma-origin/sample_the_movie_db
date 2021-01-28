package com.kotlin.project.data.repository

import com.kotlin.project.data.dao.ResultMovieDataDao
import com.kotlin.project.data.entities.ResultMovieData
import javax.inject.Inject

interface ResultMovieDataRepository {
    suspend fun getMovie(): List<ResultMovieData>
    suspend fun findId(id: Int): ResultMovieData?
    suspend fun deleteAll()
    suspend fun insert(resultMovieData: ResultMovieData)
    suspend fun delete(resultMovieData: ResultMovieData)
}

class ResultMovieDataRepositoryImpl @Inject constructor(
    private val dao: ResultMovieDataDao
) : ResultMovieDataRepository {

    override suspend fun getMovie(): List<ResultMovieData> = dao.getMovie()

    override suspend fun findId(id: Int): ResultMovieData? = dao.findId(id)

    override suspend fun deleteAll() = dao.deleteAll()

    override suspend fun insert(resultMovieData: ResultMovieData) = dao.insert(resultMovieData)

    override suspend fun delete(resultMovieData: ResultMovieData) = dao.delete(resultMovieData)
}
