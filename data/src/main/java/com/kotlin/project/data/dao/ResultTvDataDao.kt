package com.kotlin.project.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kotlin.project.data.entities.ResultTvData

@Dao
interface ResultTvDataDao {
    @Query("SELECT * FROM resultTvData")
    fun getCache(): ResultTvData

    @Query("DELETE FROM resultTvData")
    fun deleteAll()

    @Insert
    fun insert(vararg resultTvData: ResultTvData)

    @Delete
    fun delete(vararg resultTvData: ResultTvData)
}
