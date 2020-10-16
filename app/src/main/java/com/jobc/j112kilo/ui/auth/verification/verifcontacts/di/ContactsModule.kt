package com.jobc.j112kilo.ui.auth.verification.verifcontacts.di

import com.jobc.j112kilo.api.AuthApi
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.ui.auth.verification.verifcontacts.data.ContactsRepoImpl
import com.jobc.j112kilo.ui.auth.verification.verifcontacts.domain.ContactsInteractor
import com.jobc.j112kilo.ui.auth.verification.verifcontacts.domain.ContactsInteractorImpl
import com.jobc.j112kilo.ui.auth.verification.verifcontacts.domain.ContactsRepo
import dagger.Module
import dagger.Provides

@Module
class ContactsModule {

    @ScreenScope
    @Provides
    fun provideContactsInteractor(repo: ContactsRepo) : ContactsInteractor =
        ContactsInteractorImpl(repo)

    @ScreenScope
    @Provides
    fun provideContactsRepo(api: AuthApi) : ContactsRepo =
        ContactsRepoImpl(api)
}