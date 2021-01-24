package com.example.core.di.search

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.core.di.core.FragmentKey
import com.example.core.di.core.ViewModelKey
import com.example.search.SearchFragment
import com.example.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchModule {
    @Binds
    @IntoMap
    @FragmentKey(SearchFragment::class)
    abstract fun provideSearchFragment(fragment: SearchFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun provideSearchViewModel(viewModel: SearchViewModel): ViewModel
}
