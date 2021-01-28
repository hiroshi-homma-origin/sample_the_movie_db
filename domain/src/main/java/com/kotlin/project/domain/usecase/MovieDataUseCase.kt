package com.kotlin.project.domain.usecase

import com.kotlin.project.data.entities.MovieData
import com.kotlin.project.data.repository.MovieDataRepository
import javax.inject.Inject

interface ResultMovieDataUseCase {
    suspend fun getMovie(): List<MovieData>
    suspend fun findId(id: Int): MovieData?
    suspend fun deleteAll()
    suspend fun insert(movieData: MovieData)
    suspend fun delete(movieData: MovieData)
}

class ResultMovieDataUseCaseImpl @Inject constructor(
    private val repository: MovieDataRepository
) : ResultMovieDataUseCase {
    override suspend fun getMovie(): List<MovieData> = repository.getMovie()
    override suspend fun findId(id: Int): MovieData? = repository.findId(id)
    override suspend fun deleteAll() = repository.deleteAll()
    override suspend fun insert(movieData: MovieData) = repository.insert(movieData)
    override suspend fun delete(movieData: MovieData) = repository.delete(movieData)
}
