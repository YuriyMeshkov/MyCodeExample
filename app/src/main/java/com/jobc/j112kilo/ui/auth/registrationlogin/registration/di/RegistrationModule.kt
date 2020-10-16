package com.jobc.j112kilo.ui.auth.registrationlogin.registration.di

import android.content.Context
import com.jobc.j112kilo.api.AuthApi
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.storage.database.user.UserDao
import com.jobc.j112kilo.storage.database.user.UserDatabase
import com.jobc.j112kilo.storage.database.usertoken.UserTokenDao
import com.jobc.j112kilo.storage.database.usertoken.UserTokenDatabase
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.RegistrationRepoImpl
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.domain.RegistrationInteractor
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.domain.RegistrationInteractorImpl
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.domain.RegistrationRepo
import dagger.Module
import dagger.Provides

@Module
class RegistrationModule {

    @ScreenScope
    @Provides
    fun provideRegistrationInteractor(context: Context, repo: RegistrationRepo) : RegistrationInteractor =
        RegistrationInteractorImpl(context, repo)

    @ScreenScope
    @Provides
    fun provideRegistrationRepo(authApi: AuthApi,
                                databaseUserToken: UserTokenDao,
                                databaseUser: UserDao) : RegistrationRepo =
        RegistrationRepoImpl(authApi, databaseUserToken, databaseUser)

    @ScreenScope
    @Provides
    fun provideUserTokenDao(databaseUserToken: UserTokenDatabase) : UserTokenDao =
        databaseUserToken.userTokenDao()

    @ScreenScope
    @Provides
    fun provideUserDao(databaseUser: UserDatabase) : UserDao =
        databaseUser.userDao()
}