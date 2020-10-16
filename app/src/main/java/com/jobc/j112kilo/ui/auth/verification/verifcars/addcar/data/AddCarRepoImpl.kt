package com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.data

import android.graphics.Bitmap
import com.jobc.j112kilo.data.RepositoryImpl
import com.jobc.j112kilo.data.model.Car
import com.jobc.j112kilo.storage.ImageDataSource
import com.jobc.j112kilo.storage.database.cars.CarsDao
import com.jobc.j112kilo.storage.database.cars.CarsEntity
import com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.domain.AddCarRepo
import javax.inject.Inject

class AddCarRepoImpl @Inject constructor(
    private val imageDataSource: ImageDataSource,
    private val dataBaseCar: CarsDao
) : RepositoryImpl(), AddCarRepo {

    override suspend fun loadingImage(pathImage: String): Bitmap? = ioAsync {
        imageDataSource.loadingImage(pathImage)
    }

    override suspend fun deleteFile(pathFile: String) {
        imageDataSource.deleteFile(pathFile)
    }

    override suspend fun saveCarToDatabase(car: Car) : Unit = ioAsync {
        with(car) {
            dataBaseCar.insert(
                CarsEntity(
                    id = 0,
                    carId = carId,
                    carType = carType,
                    carModel = carModel,
                    carRegistrationNum = carRegistrationNum,
                    pathToCarPhoto = pathToCarPhoto,
                    carPhotoChange = carPhotoChange,
                    pathToPassportPhotoFirst = pathToPassportPhotoFirst,
                    pathToPassportPhotoSecond = pathToPassportPhotoSecond,
                    carPassportChange = carPassportChange,
                    carVerification = carVerification
                )
            )
        }
    }
}