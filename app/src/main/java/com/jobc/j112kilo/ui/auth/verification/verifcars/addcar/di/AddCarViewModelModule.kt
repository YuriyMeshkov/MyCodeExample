package com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.di

import androidx.lifecycle.ViewModel
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.di.ViewModelKey
import com.jobc.j112kilo.di.ViewModelModule
import com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.AddCarViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AddCarViewModelModule : ViewModelModule {

    @ScreenScope
    @Binds
    @IntoMap
    @ViewModelKey(AddCarViewModel::class)
    fun bindAddCarViewModelModule(viewModel: AddCarViewModel) : ViewModel
}