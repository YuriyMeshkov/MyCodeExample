package com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense.di

import androidx.lifecycle.ViewModel
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.di.ViewModelKey
import com.jobc.j112kilo.di.ViewModelModule
import com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense.DrivingLicenseViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LicenseViewModelModule: ViewModelModule {

    @ScreenScope
    @Binds
    @IntoMap
    @ViewModelKey(DrivingLicenseViewModel::class)
    fun bindLicenseViewModelModule(viewModel: DrivingLicenseViewModel) : ViewModel
}