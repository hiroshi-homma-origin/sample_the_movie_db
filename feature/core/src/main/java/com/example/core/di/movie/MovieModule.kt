package com.example.core.di.movie

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.core.di.core.FragmentKey
import com.example.core.di.core.ViewModelKey
import com.example.movie.MovieFragment
import com.example.movie.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MovieModule {
    @Binds
    @IntoMap
    @FragmentKey(MovieFragment::class)
    abstract fun provideMovieFragment(fragment: MovieFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun provideMovieViewModel(viewModel: MovieViewModel): ViewModel
}
