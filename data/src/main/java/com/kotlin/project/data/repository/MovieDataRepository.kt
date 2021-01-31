package com.kotlin.project.data.repository

import androidx.room.Update
import com.kotlin.project.data.dao.MovieDataDao
import com.kotlin.project.data.entities.MovieData
import javax.inject.Inject

interface MovieDataRepository {
    suspend fun getMovie(): List<MovieData>
    suspend fun findId(id: Int): MovieData?
    suspend fun insert(movieData: MovieData)
    suspend fun updateIsFavorite(id: Int, isUpdate: Boolean)
    suspend fun delete(movieData: MovieData)
    suspend fun deleteAll()
}

class MovieDataRepositoryImpl @Inject constructor(
    private val dao: MovieDataDao
) : MovieDataRepository {
    override suspend fun getMovie(): List<MovieData> = dao.getMovie()
    override suspend fun findId(id: Int): MovieData? = dao.findId(id)
    override suspend fun insert(movieData: MovieData) = dao.insert(movieData)
    override suspend fun updateIsFavorite(id: Int, isUpdate: Boolean) =
        dao.updateIsFavorite(id, isUpdate)

    override suspend fun delete(movieData: MovieData) = dao.delete(movieData)
    override suspend fun deleteAll() = dao.deleteAll()
}
