package com.kotlin.project.domain.di

import com.kotlin.project.data.repository.GetMovieListRepository
import com.kotlin.project.domain.usecase.SearchListUseCase
import com.kotlin.project.domain.usecase.SearchListUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideGetMovieListUseCase(
        getMovieListRepository: GetMovieListRepository
    ): SearchListUseCase {
        return SearchListUseCaseImpl(getMovieListRepository)
    }
}
