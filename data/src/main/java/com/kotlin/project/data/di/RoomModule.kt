package com.kotlin.project.data.di

import android.app.Application
import androidx.room.Room
import com.kotlin.project.data.db.TheMovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    internal fun provideTheMovieDatabase(app: Application): TheMovieDatabase =
        Room.databaseBuilder(app, TheMovieDatabase::class.java, "movie.db")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    internal fun provideCachedDataDao(db: TheMovieDatabase) =
        db.cachedDataDao()
}
