package com.jobc.j112kilo.ui.auth.verification.verifcontacts.di

import androidx.lifecycle.ViewModel
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.di.ViewModelKey
import com.jobc.j112kilo.di.ViewModelModule
import com.jobc.j112kilo.ui.auth.verification.verifcontacts.ContactsDataViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ContactsViewModelModule : ViewModelModule {

    @ScreenScope
    @Binds
    @IntoMap
    @ViewModelKey(ContactsDataViewModel::class)
    fun bindContactsViewModelModule(viewModel: ContactsDataViewModel) : ViewModel
}