package com.example.tmdb.di

import android.app.Application
import com.example.core.di.core.CoreModule
import com.example.core.di.core.NavHostModule
import com.example.core.di.core.ViewModelModule
import com.example.core.di.movie.MovieModule
import com.example.core.di.search.SearchModule
import com.example.core.di.tv.TvModule
import com.example.tmdb.MainApplication
import com.kotlin.project.data.di.NetworkModule
import com.kotlin.project.data.di.RepositoryModule
import com.kotlin.project.data.di.RoomModule
import com.kotlin.project.domain.di.UseCaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        // feature
        NavHostModule::class,
        ViewModelModule::class,
        CoreModule::class,
        MovieModule::class,
        SearchModule::class,
        TvModule::class,
        // domain
        UseCaseModule::class,
        // data
        RepositoryModule::class,
        NetworkModule::class,
        RoomModule::class
    ]
)
interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(application: MainApplication)
}
