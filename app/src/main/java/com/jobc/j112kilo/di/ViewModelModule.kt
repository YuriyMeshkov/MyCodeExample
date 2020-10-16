package com.jobc.j112kilo.di

/***
 * родительский интерфейс у которого будет именно фабрика
 * эти аннотации нужны для dagger
 */

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {
    @ScreenScope
    @Binds
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}