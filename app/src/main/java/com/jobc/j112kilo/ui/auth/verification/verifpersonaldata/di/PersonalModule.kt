package com.jobc.j112kilo.ui.auth.verification.verifpersonaldata.di

import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.storage.ImageDataSource
import com.jobc.j112kilo.ui.auth.verification.verifpersonaldata.data.PersonalRepoImpl
import com.jobc.j112kilo.ui.auth.verification.verifpersonaldata.domain.PersonalInteractor
import com.jobc.j112kilo.ui.auth.verification.verifpersonaldata.domain.PersonalInteractorImpl
import com.jobc.j112kilo.ui.auth.verification.verifpersonaldata.domain.PersonalRepo
import dagger.Module
import dagger.Provides

@Module
class PersonalModule {

    @ScreenScope
    @Provides
    fun providerPersonal(imageDataSource: ImageDataSource) : PersonalRepo =
        PersonalRepoImpl(imageDataSource)

    @ScreenScope
    @Provides
    fun providerPersonalInteractor(repo: PersonalRepo) : PersonalInteractor =
        PersonalInteractorImpl(repo)
}