package com.kotlin.project.domain.usecase

import com.kotlin.project.data.entities.MovieData
import com.kotlin.project.data.entities.TvData
import com.kotlin.project.data.repository.MovieDataRepository
import javax.inject.Inject

interface ResultMovieDataUseCase {
    suspend fun getMovie(): List<MovieData>
    suspend fun getFavoriteMovie(): List<MovieData>
    suspend fun findId(id: Int): MovieData?
    suspend fun insert(movieData: MovieData)
    suspend fun updateIsFavorite(id: Int, isUpdate: Boolean)
    suspend fun delete(movieData: MovieData)
    suspend fun deleteAll()
}

class ResultMovieDataUseCaseImpl @Inject constructor(
    private val repository: MovieDataRepository
) : ResultMovieDataUseCase {
    override suspend fun getMovie(): List<MovieData> = repository.getMovie()
    override suspend fun getFavoriteMovie(): List<MovieData> = repository.getFavoriteMovie()
    override suspend fun findId(id: Int): MovieData? = repository.findId(id)
    override suspend fun insert(movieData: MovieData) = repository.insert(movieData)
    override suspend fun updateIsFavorite(id: Int, isUpdate: Boolean) = repository.updateIsFavorite(id, isUpdate)
    override suspend fun delete(movieData: MovieData) = repository.delete(movieData)
    override suspend fun deleteAll() = repository.deleteAll()
}
