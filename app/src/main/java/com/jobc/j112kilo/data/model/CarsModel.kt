package com.jobc.j112kilo.data.model

import android.graphics.Bitmap

data class CarsModel(
    val listCar: MutableList<Car> = mutableListOf()
) : Model

data class Car(
    var carId: String = "",
    var carType: String = "",
    var carModel: String= "",
    var carRegistrationNum: String = "",
    var pathToCarPhoto: String? = null,
    var carPhotoChange: Boolean= false,
    var pathToPassportPhotoFirst: String? = null,
    var photoPassportFirst: Bitmap? = null,
    var pathToPassportPhotoSecond: String? = null,
    var photoPassportSecond: Bitmap? =null,
    var carPassportChange: Boolean = false,
    var carVerification: Boolean = false,
    var carPhoto: Bitmap? = null
) : Model

