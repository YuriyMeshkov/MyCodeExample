package com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense.di

import android.content.Context
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.storage.ImageDataSource
import com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense.data.LicenseRepoImpl
import com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense.domain.LicenseInteractor
import com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense.domain.LicenseInteractorImpl
import com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense.domain.LicenseRepo
import dagger.Module
import dagger.Provides

@Module
class LicenseModule {

    @ScreenScope
    @Provides
    fun providerLicenseRepo(imageDataSource: ImageDataSource) : LicenseRepo =
        LicenseRepoImpl(imageDataSource)

    @ScreenScope
    @Provides
    fun providerLicenseInteractor(context: Context, repo: LicenseRepo) : LicenseInteractor =
        LicenseInteractorImpl(context, repo)
}