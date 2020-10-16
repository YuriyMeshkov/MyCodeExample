package com.jobc.j112kilo.ui.auth.verification.verifcars.data.model

import android.content.res.Resources
import com.jobc.j112kilo.R
import com.jobc.j112kilo.data.model.Model

data class CarType(
    val carType: String,
    val choice: Boolean = false
) : Model

class CarsType {

    private val carsType = mutableListOf<CarType>()

    fun getCarsType(resources: Resources) : List<CarType> {
        carsType.add(
            CarType(
            carType = resources.getString(R.string.car_to_250_kg)
            )
        )
        carsType.add(
            CarType(
                carType = resources.getString(R.string.van_car_to_500_kg)
            )
        )
        carsType.add(
            CarType(
                carType = resources.getString(R.string.minibus_to_1000_kg)
            )
        )
        carsType.add(
            CarType(
                carType = resources.getString(R.string.truck_to_1500_kg)
            )
        )
        carsType.add(
            CarType(
                carType = resources.getString(R.string.truck_to_3500_kg)
            )
        )
        carsType.add(
            CarType(
                carType = resources.getString(R.string.truck_to_5000_kg)
            )
        )
        carsType.add(
            CarType(
                carType = resources.getString(R.string.truck_over_5000_kg)
            )
        )
        return carsType
    }
}