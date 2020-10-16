package com.jobc.j112kilo.ui.auth.splash.data

import android.graphics.Bitmap
import com.jobc.j112kilo.data.RepositoryImpl
import com.jobc.j112kilo.data.model.CarsModel
import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.data.model.UserTokenModel
import com.jobc.j112kilo.storage.ImageDataSource
import com.jobc.j112kilo.storage.database.cars.CarsDao
import com.jobc.j112kilo.storage.database.user.UserDao
import com.jobc.j112kilo.storage.database.usertoken.UserTokenDao
import com.jobc.j112kilo.ui.auth.splash.data.mapper.CarsEntityToModelMapper
import com.jobc.j112kilo.ui.auth.splash.data.mapper.UserEntityToModelMapper
import com.jobc.j112kilo.ui.auth.splash.data.mapper.UserTokenEntityToModelMapper
import com.jobc.j112kilo.ui.auth.splash.domain.SplashRepo
import javax.inject.Inject

class SplashRepoImpl @Inject constructor(
    private val dataBaseUserToken: UserTokenDao,
    private val dataBaseUser: UserDao,
    private val dataBaseCars: CarsDao,
    private val imageDataSource: ImageDataSource
) : RepositoryImpl(), SplashRepo {

    override suspend fun getUserToken(): UserTokenModel? = ioAsync {
        val result = dataBaseUserToken.getFromDataBaseUserToken()
        when(result != null) {
            true -> UserTokenEntityToModelMapper().map(result)
            false -> null
        }
    }

    override suspend fun getUserData(): UserModel? = ioAsync {
        val result = dataBaseUser.getFromDataBaseUser()
        when(result != null) {
            true -> {
                val user = UserEntityToModelMapper().map(result)
                if (user.pathToAvatar.isNotEmpty()) {
                    user.avatar = loadingImage(user.pathToAvatar)
                }
                if (user.pathToPhotoFirstLicense.isNotEmpty()) {
                    user.photoLicenseFirst = loadingImage(user.pathToPhotoFirstLicense)
                }
                if (user.pathToPhotoSecondLicense.isNotEmpty()) {
                    user.photoLicenseSecond = loadingImage(user.pathToPhotoSecondLicense)
                }
                user
            }
            false -> null
        }
    }

    override suspend fun getCarsData(): CarsModel? = ioAsync {
        val result = dataBaseCars.getCarsFromDatabaseCars()
        when(result != null) {
            true -> {
                val cars = CarsEntityToModelMapper().map(result)
                cars.listCar.forEach { car ->
                    car.pathToCarPhoto?.let {
                        car.carPhoto = loadingImage(it)
                    }
                    car.pathToPassportPhotoFirst?.let {
                        car.photoPassportFirst = loadingImage(it)
                    }
                    car.pathToPassportPhotoSecond?.let {
                        car.photoPassportSecond = loadingImage(it)
                    }
                }
                cars
            }
            false -> null
        }
    }

    override suspend fun loadingImage(pathImage: String): Bitmap? = ioAsync {
        imageDataSource.loadingImage(pathImage)
    }
}