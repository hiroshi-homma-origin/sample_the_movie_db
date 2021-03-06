package com.example.core.di.core

import com.example.core.ui.CoreActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CoreModule {
    @ContributesAndroidInjector
    abstract fun contributeCoreActivity(): CoreActivity
}
