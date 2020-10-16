package com.jobc.j112kilo.ui.auth.splash.data.mapper

import com.jobc.j112kilo.data.mapper.MapperFromDatabaseForList
import com.jobc.j112kilo.data.model.Car
import com.jobc.j112kilo.data.model.CarsModel
import com.jobc.j112kilo.storage.database.cars.CarsEntity

class CarsEntityToModelMapper : MapperFromDatabaseForList<List<CarsEntity>, CarsModel>() {
    override fun map(from: List<CarsEntity>): CarsModel {
        val cars = CarsModel()
        from.forEach {
            cars.listCar.add(
                Car(
                    carId = it.carId,
                    carType = it.carType,
                    carModel = it.carModel,
                    carRegistrationNum = it.carRegistrationNum,
                    pathToCarPhoto = it.pathToCarPhoto,
                    carPhotoChange = it.carPhotoChange,
                    pathToPassportPhotoFirst = it.pathToPassportPhotoFirst,
                    pathToPassportPhotoSecond = it.pathToPassportPhotoSecond,
                    carPassportChange = it.carPassportChange,
                    carVerification = it.carVerification
                )
            )
        }
        return cars
    }
}