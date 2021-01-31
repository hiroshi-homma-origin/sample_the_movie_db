package com.kotlin.project.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kotlin.project.data.entities.TvData

@Dao
interface TvDataDao {

    @Query("SELECT * FROM tvData")
    fun getTv(): List<TvData>

    @Query("SELECT * FROM tvData WHERE id = :id")
    fun findId(id: Int): TvData?

    @Query("UPDATE tvData SET isFavorite = :isUpdate WHERE uid = :id")
    fun updateIsFavorite(id: Int, isUpdate: Boolean)

    @Query("DELETE FROM tvData")
    fun deleteAll()

    @Insert
    fun insert(vararg tvData: TvData)

    @Delete
    fun delete(vararg tvData: TvData)
}
