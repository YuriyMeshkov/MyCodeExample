package com.jobc.j112kilo.di

import android.content.Context
import com.jobc.j112kilo.api.AuthApi
import com.jobc.j112kilo.data.model.CarsModel
import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.data.model.UserTokenModel
import com.jobc.j112kilo.storage.ImageDataSource
import com.jobc.j112kilo.storage.database.cars.CarsDatabase
import com.jobc.j112kilo.storage.database.user.UserDatabase
import com.jobc.j112kilo.storage.database.usertoken.UserTokenDatabase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(modules =  [StorageModule::class, ModelModule::class, NetworkModule::class])
@Singleton
interface AppComponent {

    fun context() : Context
    fun imageDataSource() : ImageDataSource
    fun cars() : CarsModel
    fun authApi() : AuthApi
    fun userTokenModel(): UserTokenModel
    fun userModel(): UserModel
    fun userTokenDatabase(): UserTokenDatabase
    fun userDatabase(): UserDatabase
    fun carsDatabase(): CarsDatabase

    @Component.Builder
    interface Builder {
        fun build() : AppComponent

        @BindsInstance
        fun context(context: Context) : Builder
    }

}