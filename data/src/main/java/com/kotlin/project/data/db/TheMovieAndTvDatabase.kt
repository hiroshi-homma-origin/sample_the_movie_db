package com.kotlin.project.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kotlin.project.data.dao.ResultMovieDataDao
import com.kotlin.project.data.dao.ResultTvDataDao
import com.kotlin.project.data.entities.ResultMovieData
import com.kotlin.project.data.entities.ResultTvData

@Database(entities = [ResultMovieData::class, ResultTvData::class], version = 1)
@TypeConverters(Converters::class)
abstract class TheMovieAndTvDatabase : RoomDatabase() {
    abstract fun movieDataDao(): ResultMovieDataDao
    abstract fun tvDataDao(): ResultTvDataDao
}
