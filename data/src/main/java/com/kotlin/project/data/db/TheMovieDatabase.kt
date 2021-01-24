package com.kotlin.project.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kotlin.project.data.dao.ResultMovieDataDao
import com.kotlin.project.data.entities.ResultMovieData

@Database(entities = [ResultMovieData::class], version = 1)
@TypeConverters(Converters::class)
abstract class TheMovieDatabase : RoomDatabase() {
    abstract fun cachedDataDao(): ResultMovieDataDao
}
