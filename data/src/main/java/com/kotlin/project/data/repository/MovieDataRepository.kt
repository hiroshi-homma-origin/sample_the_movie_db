package com.kotlin.project.data.repository

import com.kotlin.project.data.dao.MovieDataDao
import com.kotlin.project.data.entities.transform
import com.kotlin.project.data.model.search.SearchMovieData
import com.kotlin.project.data.model.search.transform
import javax.inject.Inject

interface MovieDataRepository {
    suspend fun getMovie(): List<SearchMovieData>
    suspend fun getFavoriteMovie(): List<SearchMovieData>
    suspend fun findId(id: Int): SearchMovieData?
    suspend fun insert(searchMovieData: SearchMovieData)
    suspend fun updateIsFavorite(id: Int, isUpdate: Boolean)
    suspend fun delete(searchMovieData: SearchMovieData)
    suspend fun deleteAll()
}

class MovieDataRepositoryImpl @Inject constructor(
    private val dao: MovieDataDao
) : MovieDataRepository {
    override suspend fun getMovie(): List<SearchMovieData> =
        dao.getMovie().map { it.transform() }

    override suspend fun getFavoriteMovie(): List<SearchMovieData> =
        dao.getFavoriteMovie().map { it.transform() }

    override suspend fun findId(id: Int): SearchMovieData? = dao.findId(id)?.transform()

    override suspend fun insert(searchMovieData: SearchMovieData) =
        dao.insert(searchMovieData.transform())

    override suspend fun updateIsFavorite(id: Int, isUpdate: Boolean) =
        dao.updateIsFavorite(id, isUpdate)

    override suspend fun delete(searchMovieData: SearchMovieData) =
        dao.delete(searchMovieData.transform())

    override suspend fun deleteAll() = dao.deleteAll()
}
