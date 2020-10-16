package com.jobc.j112kilo.ui.auth.registrationlogin.login.di

import androidx.lifecycle.ViewModel
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.di.ViewModelKey
import com.jobc.j112kilo.di.ViewModelModule
import com.jobc.j112kilo.ui.auth.registrationlogin.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LoginViewModelModule : ViewModelModule {
    @ScreenScope
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindsLoginViewModel(viewModel: LoginViewModel) : ViewModel
}