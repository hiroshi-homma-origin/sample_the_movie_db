package com.kotlin.project.domain.usecase

import com.kotlin.project.data.model.search.SearchMovieData
import com.kotlin.project.data.repository.MovieDataRepository
import javax.inject.Inject

interface ResultMovieDataUseCase {
    suspend fun getMovie(): List<SearchMovieData>
    suspend fun getFavoriteMovie(): List<SearchMovieData>
    suspend fun findId(id: Int): SearchMovieData?
    suspend fun insert(searchMovieData: SearchMovieData)
    suspend fun updateIsFavorite(id: Int, isUpdate: Boolean)
    suspend fun delete(searchMovieData: SearchMovieData)
    suspend fun deleteAll()
}

class ResultMovieDataUseCaseImpl @Inject constructor(
    private val repository: MovieDataRepository
) : ResultMovieDataUseCase {
    override suspend fun getMovie(): List<SearchMovieData> = repository.getMovie()

    override suspend fun getFavoriteMovie(): List<SearchMovieData> = repository.getFavoriteMovie()

    override suspend fun findId(id: Int): SearchMovieData? = repository.findId(id)

    override suspend fun insert(searchMovieData: SearchMovieData) =
        repository.insert(searchMovieData)

    override suspend fun updateIsFavorite(id: Int, isUpdate: Boolean) =
        repository.updateIsFavorite(id, isUpdate)

    override suspend fun delete(searchMovieData: SearchMovieData) =
        repository.delete(searchMovieData)

    override suspend fun deleteAll() = repository.deleteAll()
}
