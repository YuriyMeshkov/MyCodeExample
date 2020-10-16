package com.jobc.j112kilo.ui.auth.verification.di

import com.jobc.j112kilo.api.AuthApi
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.storage.database.user.UserDao
import com.jobc.j112kilo.storage.database.user.UserDatabase
import com.jobc.j112kilo.storage.database.usertoken.UserTokenDao
import com.jobc.j112kilo.storage.database.usertoken.UserTokenDatabase
import com.jobc.j112kilo.ui.auth.verification.data.VerificationRepoImpl
import com.jobc.j112kilo.ui.auth.verification.domain.VerificationInteractor
import com.jobc.j112kilo.ui.auth.verification.domain.VerificationInteractorImpl
import com.jobc.j112kilo.ui.auth.verification.domain.VerificationRepo
import dagger.Module
import dagger.Provides

@Module
class VerificationModule {

    @ScreenScope
    @Provides
    fun providerVerificationInteractor(repo: VerificationRepo) : VerificationInteractor =
        VerificationInteractorImpl(repo)

    @ScreenScope
    @Provides
    fun provideVerificationRepo(
        authApi: AuthApi,
        databaseUserToken: UserTokenDao,
        databaseUser: UserDao
    ) : VerificationRepo = VerificationRepoImpl(authApi, databaseUserToken, databaseUser)

    @ScreenScope
    @Provides
    fun provideUserTokenDao(databaseUserToken: UserTokenDatabase) : UserTokenDao =
        databaseUserToken.userTokenDao()

    @ScreenScope
    @Provides
    fun provideUserDao(databaseUser: UserDatabase) : UserDao =
        databaseUser.userDao()
}