package com.kotlin.project.data.di

import com.kotlin.project.data.api.TheMovieDBApi
import com.kotlin.project.data.dao.ResultMovieDataDao
import com.kotlin.project.data.dao.ResultTvDataDao
import com.kotlin.project.data.repository.DetailDataRepository
import com.kotlin.project.data.repository.DetailDataRepositoryImpl
import com.kotlin.project.data.repository.ResultMovieDataRepository
import com.kotlin.project.data.repository.ResultMovieDataRepositoryImpl
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
    fun provideResultMovieDataRepository(dao: ResultMovieDataDao): ResultMovieDataRepository {
        return ResultMovieDataRepositoryImpl(dao)
    }

    @Provides
    fun provideResultTvDataRepository(dao: ResultTvDataDao): ResultTvDataRepository {
        return ResultTvDataRepositoryImpl(dao)
    }
}
