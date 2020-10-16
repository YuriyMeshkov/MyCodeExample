package com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.di

import androidx.lifecycle.ViewModel
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.di.ViewModelKey
import com.jobc.j112kilo.di.ViewModelModule
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.CityChoiceFromGoogleViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CityChoiceViewModelModule : ViewModelModule {

    @ScreenScope
    @Binds
    @IntoMap
    @ViewModelKey(CityChoiceFromGoogleViewModel::class)
    fun bindsCityChoiceFromGoogleViewModule(viewModel: CityChoiceFromGoogleViewModel) : ViewModel
}