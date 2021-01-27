package com.kotlin.project.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kotlin.project.data.entities.ResultMovieData

@Dao
interface ResultMovieDataDao {

    @Query("SELECT * FROM resultMovieData LIMIT 20")
    fun get20Data(): List<ResultMovieData>

    @Query("SELECT * FROM resultMovieData")
    fun getMovie(): List<ResultMovieData>

    @Query("SELECT * FROM resultMovieData WHERE id = :id")
    fun findId(id: Int): ResultMovieData?

    @Query("DELETE FROM resultMovieData")
    fun deleteAll()

    @Insert
    fun insert(vararg resultMovieData: ResultMovieData)

    @Delete
    fun delete(vararg resultMovieData: ResultMovieData)
}
