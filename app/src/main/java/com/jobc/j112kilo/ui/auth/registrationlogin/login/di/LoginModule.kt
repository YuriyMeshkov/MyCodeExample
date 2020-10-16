package com.jobc.j112kilo.ui.auth.registrationlogin.login.di

import android.content.Context
import com.jobc.j112kilo.api.AuthApi
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.storage.database.usertoken.UserTokenDao
import com.jobc.j112kilo.storage.database.usertoken.UserTokenDatabase
import com.jobc.j112kilo.ui.auth.registrationlogin.login.data.LoginRepoImpl
import com.jobc.j112kilo.ui.auth.registrationlogin.login.domain.LoginInteractor
import com.jobc.j112kilo.ui.auth.registrationlogin.login.domain.LoginInteractorImpl
import com.jobc.j112kilo.ui.auth.registrationlogin.login.domain.LoginRepo
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @ScreenScope
    @Provides
    fun provideLoginInteractor(context: Context, repo: LoginRepo) : LoginInteractor =
        LoginInteractorImpl(context, repo)

    @ScreenScope
    @Provides
    fun provideLoginRepo(authApi: AuthApi, dataBase: UserTokenDao) : LoginRepo =
        LoginRepoImpl(authApi, dataBase)

    @ScreenScope
    @Provides
    fun provideUserTokenDao(database: UserTokenDatabase) : UserTokenDao =
        database.userTokenDao()
}