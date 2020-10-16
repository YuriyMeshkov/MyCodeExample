package com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.domain

import com.jobc.j112kilo.data.model.Car
import com.jobc.j112kilo.domain.Interactor

interface AddCarInteractor : Interactor<AddCarInteractorOut> {

    fun addCarDataChanged(
        carType: String,
        carModel:String,
        carRegistrationNum: String,
        carPhotoChange: Boolean,
        carPassportChange: Boolean
    )
    fun isEnterString(carType: String) = carType.trim().isNotEmpty()
    fun loadingImage(pathImage: String)
    fun deleteFile(pathFile: String)
    fun saveCarToDatabase (car: Car)

}