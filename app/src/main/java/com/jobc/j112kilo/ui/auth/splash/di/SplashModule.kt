package com.jobc.j112kilo.ui.auth.splash.di

import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.storage.ImageDataSource
import com.jobc.j112kilo.storage.database.cars.CarsDao
import com.jobc.j112kilo.storage.database.cars.CarsDatabase
import com.jobc.j112kilo.storage.database.user.UserDao
import com.jobc.j112kilo.storage.database.user.UserDatabase
import com.jobc.j112kilo.storage.database.usertoken.UserTokenDao
import com.jobc.j112kilo.storage.database.usertoken.UserTokenDatabase
import com.jobc.j112kilo.ui.auth.splash.data.SplashRepoImpl
import com.jobc.j112kilo.ui.auth.splash.domain.SplashInteractor
import com.jobc.j112kilo.ui.auth.splash.domain.SplashInteractorImpl
import com.jobc.j112kilo.ui.auth.splash.domain.SplashRepo
import dagger.Module
import dagger.Provides

@Module
class SplashModule {

    @ScreenScope
    @Provides
    fun provideSplashInteractor(repo: SplashRepo) : SplashInteractor =
        SplashInteractorImpl(repo)

    @ScreenScope
    @Provides
    fun provideSplashRepo(
        databaseUserToken: UserTokenDao,
        databaseUser: UserDao,
        databaseCars: CarsDao,
        imageDataSource: ImageDataSource
    ) : SplashRepo =
        SplashRepoImpl(
            databaseUserToken,
            databaseUser,
            databaseCars,
            imageDataSource
        )

    @ScreenScope
    @Provides
    fun provideUserTokenDao(databaseUserToken: UserTokenDatabase) : UserTokenDao =
        databaseUserToken.userTokenDao()

    @ScreenScope
    @Provides
    fun provideUserDao(databaseUser: UserDatabase) : UserDao =
        databaseUser.userDao()

    @ScreenScope
    @Provides
    fun provideCarsDao(databaseCars: CarsDatabase) : CarsDao =
        databaseCars.carsDao()
}