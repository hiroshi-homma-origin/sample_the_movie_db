package com.kotlin.project.domain.di

import com.kotlin.project.data.repository.GetMovieListRepository
import com.kotlin.project.domain.usecase.GetMovieListUseCase
import com.kotlin.project.domain.usecase.GetMovieListUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideGetMovieListUseCase(
        getMovieListRepository: GetMovieListRepository
    ): GetMovieListUseCase {
        return GetMovieListUseCaseImpl(getMovieListRepository)
    }
}
