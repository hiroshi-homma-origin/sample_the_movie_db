package com.kotlin.project.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kotlin.project.data.entities.MovieData

@Dao
interface MovieDataDao {

    @Query("SELECT * FROM movieData")
    fun getMovie(): List<MovieData>

    @Query("SELECT * FROM movieData WHERE isFavorite = 1")
    fun getFavoriteMovie(): List<MovieData>

    @Query("SELECT * FROM movieData WHERE id = :id")
    fun findId(id: Int): MovieData?

    @Query("UPDATE movieData SET isFavorite = :isUpdate WHERE id = :id")
    fun updateIsFavorite(id: Int, isUpdate: Boolean)

    @Query("DELETE FROM movieData")
    fun deleteAll()

    @Insert
    fun insert(vararg movieData: MovieData)

    @Delete
    fun delete(vararg movieData: MovieData)
}
