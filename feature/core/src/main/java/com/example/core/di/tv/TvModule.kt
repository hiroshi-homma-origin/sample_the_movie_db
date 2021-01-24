package com.example.core.di.tv

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.core.di.core.FragmentKey
import com.example.core.di.core.ViewModelKey
import com.example.tv.TvFragment
import com.example.tv.TvViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TvModule {
    @Binds
    @IntoMap
    @FragmentKey(TvFragment::class)
    abstract fun provideTvFragment(fragment: TvFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(TvViewModel::class)
    abstract fun provideTvViewModel(viewModel: TvViewModel): ViewModel
}
