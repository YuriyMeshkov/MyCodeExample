package com.jobc.j112kilo.di

import com.jobc.j112kilo.data.model.CarsModel
import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.data.model.UserTokenModel
import com.jobc.j112kilo.storage.database.cars.CarsDao
import com.jobc.j112kilo.storage.database.cars.CarsDatabase
import com.jobc.j112kilo.storage.database.user.UserDao
import com.jobc.j112kilo.storage.database.user.UserDatabase
import com.jobc.j112kilo.storage.database.usertoken.UserTokenDao
import com.jobc.j112kilo.storage.database.usertoken.UserTokenDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModelModule {

    @Provides
    @Singleton
    fun provideCarsModel() = CarsModel()

    @Provides
    @Singleton
    fun provideUserTokenModel() = UserTokenModel()

    @Provides
    @Singleton
    fun provideUserModel() = UserModel()

}
