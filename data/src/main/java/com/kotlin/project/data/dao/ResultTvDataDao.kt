package com.kotlin.project.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kotlin.project.data.entities.ResultTvData

@Dao
interface ResultTvDataDao {

    @Query("SELECT * FROM resultTvData LIMIT 20")
    fun get20Data(): List<ResultTvData>

    @Query("SELECT * FROM resultTvData")
    fun getTv(): List<ResultTvData>

    @Query("SELECT * FROM resultTvData WHERE id = :id")
    fun findId(id: Int): ResultTvData?

    @Query("DELETE FROM resultTvData")
    fun deleteAll()

    @Insert
    fun insert(vararg resultTvData: ResultTvData)

    @Delete
    fun delete(vararg resultTvData: ResultTvData)
}
