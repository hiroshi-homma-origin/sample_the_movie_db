package com.kotlin.project.domain.di

import com.kotlin.project.data.repository.DetailDataRepository
import com.kotlin.project.data.repository.MovieDataRepository
import com.kotlin.project.data.repository.ResultTvDataRepository
import com.kotlin.project.data.repository.SearchListRepository
import com.kotlin.project.domain.usecase.DetailDataUseCase
import com.kotlin.project.domain.usecase.DetailDataUseCaseImpl
import com.kotlin.project.domain.usecase.ResultMovieDataUseCase
import com.kotlin.project.domain.usecase.ResultMovieDataUseCaseImpl
import com.kotlin.project.domain.usecase.ResultTvDataUseCase
import com.kotlin.project.domain.usecase.ResultTvDataUseCaseImpl
import com.kotlin.project.domain.usecase.SearchListUseCase
import com.kotlin.project.domain.usecase.SearchListUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideSearchListUseCase(
        searchListRepository: SearchListRepository
    ): SearchListUseCase {
        return SearchListUseCaseImpl(searchListRepository)
    }

    @Provides
    fun provideDetailDataUseCase(
        detailDataRepository: DetailDataRepository
    ): DetailDataUseCase {
        return DetailDataUseCaseImpl(detailDataRepository)
    }

    @Provides
    fun provideResultMovieDataUseCase(repository: MovieDataRepository): ResultMovieDataUseCase {
        return ResultMovieDataUseCaseImpl(repository)
    }

    @Provides
    fun provideResultTvDataUseCase(repository: ResultTvDataRepository): ResultTvDataUseCase {
        return ResultTvDataUseCaseImpl(repository)
    }
}
