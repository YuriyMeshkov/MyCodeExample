package com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.di

import android.content.Context
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.storage.ImageDataSource
import com.jobc.j112kilo.storage.database.cars.CarsDao
import com.jobc.j112kilo.storage.database.cars.CarsDatabase
import com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.data.AddCarRepoImpl
import com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.domain.AddCarInteractor
import com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.domain.AddCarInteractorImpl
import com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.domain.AddCarRepo
import dagger.Module
import dagger.Provides

@Module
class AddCarsModule {

    @ScreenScope
    @Provides
    fun providerAddCarInteractor(context: Context, repo: AddCarRepo) : AddCarInteractor =
        AddCarInteractorImpl(context, repo)

    @ScreenScope
    @Provides
    fun providerAddCarRepo(
        imageDataSource: ImageDataSource,
        databaseCar: CarsDao
    ) : AddCarRepo = AddCarRepoImpl(imageDataSource, databaseCar)

    @ScreenScope
    @Provides
    fun provideCarsDao(databaseCar: CarsDatabase) : CarsDao = databaseCar.carsDao()
}