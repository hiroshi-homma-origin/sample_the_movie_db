package com.kotlin.project.domain.usecase

import com.kotlin.project.data.entities.ResultMovieData
import com.kotlin.project.data.repository.ResultMovieDataRepository
import javax.inject.Inject

interface ResultMovieDataUseCase {
    suspend fun get20Data(): ResultMovieData
    suspend fun getMovie(): ResultMovieData
    suspend fun deleteAll()
    suspend fun insert(resultMovieData: ResultMovieData)
    suspend fun delete(resultMovieData: ResultMovieData)
}

class ResultMovieDataUseCaseImpl @Inject constructor(
    private val repository: ResultMovieDataRepository
) : ResultMovieDataUseCase {
    override suspend fun get20Data(): ResultMovieData = repository.get20Data()
    override suspend fun getMovie(): ResultMovieData = repository.getMovie()
    override suspend fun deleteAll() = repository.deleteAll()
    override suspend fun insert(resultMovieData: ResultMovieData) = repository.insert(resultMovieData)
    override suspend fun delete(resultMovieData: ResultMovieData) = repository.delete(resultMovieData)
}