package com.jobc.j112kilo.ui.auth.splash.di

import androidx.lifecycle.ViewModel
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.di.ViewModelKey
import com.jobc.j112kilo.di.ViewModelModule
import com.jobc.j112kilo.ui.auth.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SplashViewModelModule : ViewModelModule {

    @ScreenScope
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindsSplashViewModel(viewModel: SplashViewModel) : ViewModel
}