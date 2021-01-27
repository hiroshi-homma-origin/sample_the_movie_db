package com.kotlin.project.data.di

import android.app.Application
import androidx.room.Room
import com.kotlin.project.data.db.TheMovieAndTvDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    internal fun provideTheMovieDatabase(app: Application): TheMovieAndTvDatabase =
        Room.databaseBuilder(app, TheMovieAndTvDatabase::class.java, "movieAndTv.db")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    internal fun provideMovieDataDao(db: TheMovieAndTvDatabase) =
        db.movieDataDao()

    @Singleton
    @Provides
    internal fun provideTvDataDao(db: TheMovieAndTvDatabase) =
        db.tvDataDao()
}
