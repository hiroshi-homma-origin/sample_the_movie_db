package com.kotlin.project.data.repository

import com.kotlin.project.data.dao.MovieDataDao
import com.kotlin.project.data.entities.MovieData
import javax.inject.Inject

interface MovieDataRepository {
    suspend fun getMovie(): List<MovieData>
    suspend fun findId(id: Int): MovieData?
    suspend fun deleteAll()
    suspend fun insert(movieData: MovieData)
    suspend fun delete(movieData: MovieData)
}

class MovieDataRepositoryImpl @Inject constructor(
    private val dao: MovieDataDao
) : MovieDataRepository {

    override suspend fun getMovie(): List<MovieData> = dao.getMovie()

    override suspend fun findId(id: Int): MovieData? = dao.findId(id)

    override suspend fun deleteAll() = dao.deleteAll()

    override suspend fun insert(movieData: MovieData) = dao.insert(movieData)

    override suspend fun delete(movieData: MovieData) = dao.delete(movieData)
}
