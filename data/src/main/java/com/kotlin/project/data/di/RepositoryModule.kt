package com.kotlin.project.data.di

import com.kotlin.project.data.api.TheMovieDBApi
import com.kotlin.project.data.dao.MovieDataDao
import com.kotlin.project.data.dao.TvDataDao
import com.kotlin.project.data.repository.DetailDataRepository
import com.kotlin.project.data.repository.DetailDataRepositoryImpl
import com.kotlin.project.data.repository.MovieDataRepository
import com.kotlin.project.data.repository.MovieDataRepositoryImpl
import com.kotlin.project.data.repository.ResultTvDataRepository
import com.kotlin.project.data.repository.ResultTvDataRepositoryImpl
import com.kotlin.project.data.repository.SearchListRepository
import com.kotlin.project.data.repository.SearchListRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideSearchListRepository(theMovieDBApi: TheMovieDBApi): SearchListRepository {
        return SearchListRepositoryImpl(theMovieDBApi)
    }

    @Provides
    fun provideDetailDataRepository(theMovieDBApi: TheMovieDBApi): DetailDataRepository {
        return DetailDataRepositoryImpl(theMovieDBApi)
    }

    @Provides
    fun provideResultMovieDataRepository(dao: MovieDataDao): MovieDataRepository {
        return MovieDataRepositoryImpl(dao)
    }

    @Provides
    fun provideResultTvDataRepository(dao: TvDataDao): ResultTvDataRepository {
        return ResultTvDataRepositoryImpl(dao)
    }
}
