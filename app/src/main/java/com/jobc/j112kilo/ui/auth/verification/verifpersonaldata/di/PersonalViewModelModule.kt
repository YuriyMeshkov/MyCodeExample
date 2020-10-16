package com.jobc.j112kilo.ui.auth.verification.verifpersonaldata.di

import androidx.lifecycle.ViewModel
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.di.ViewModelKey
import com.jobc.j112kilo.di.ViewModelModule
import com.jobc.j112kilo.ui.auth.verification.verifpersonaldata.PersonalDataViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PersonalViewModelModule : ViewModelModule {

    @ScreenScope
    @Binds
    @IntoMap
    @ViewModelKey(PersonalDataViewModel::class)
    fun bindPersonalViewModelModule(viewModel: PersonalDataViewModel) : ViewModel
}