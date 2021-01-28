package com.kotlin.project.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kotlin.project.data.dao.MovieDataDao
import com.kotlin.project.data.dao.TvDataDao
import com.kotlin.project.data.entities.MovieData
import com.kotlin.project.data.entities.TvData

@Database(entities = [MovieData::class, TvData::class], version = 1)
@TypeConverters(Converters::class)
abstract class TheMovieAndTvDatabase : RoomDatabase() {
    abstract fun movieDataDao(): MovieDataDao
    abstract fun tvDataDao(): TvDataDao
}
