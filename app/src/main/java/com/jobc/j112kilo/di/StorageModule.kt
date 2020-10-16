package com.jobc.j112kilo.di

import android.content.Context
import androidx.room.Room
import com.jobc.j112kilo.storage.ImageDataSource
import com.jobc.j112kilo.storage.database.cars.CarsDatabase
import com.jobc.j112kilo.storage.database.user.UserDatabase
import com.jobc.j112kilo.storage.database.usertoken.UserTokenDatabase
import com.jobc.j112kilo.utils.CARS_DATABASE
import com.jobc.j112kilo.utils.USER_DATABASE
import com.jobc.j112kilo.utils.USER_TOKEN_DATABASE
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    @Singleton
    fun provideImageDataSource() : ImageDataSource = ImageDataSource()

    @Provides
    @Singleton
    fun provideUserTokenDatabase(context: Context) : UserTokenDatabase =
        Room.databaseBuilder(context, UserTokenDatabase::class.java, USER_TOKEN_DATABASE).build()

    @Provides
    @Singleton
    fun provideUserDatabase(context: Context) : UserDatabase =
        Room.databaseBuilder(context, UserDatabase::class.java, USER_DATABASE).build()

    @Provides
    @Singleton
    fun provideCarsDatabase(context: Context) : CarsDatabase =
        Room.databaseBuilder(context, CarsDatabase::class.java, CARS_DATABASE).build()
}