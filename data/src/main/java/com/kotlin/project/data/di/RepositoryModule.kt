package com.kotlin.project.data.di

import com.kotlin.project.data.api.TheMovieDBApi
import com.kotlin.project.data.repository.GetMovieListRepository
import com.kotlin.project.data.repository.GetMovieListRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideGetMovieListRepository(theMovieDBApi: TheMovieDBApi): GetMovieListRepository {
        return GetMovieListRepositoryImpl(theMovieDBApi)
    }
}
