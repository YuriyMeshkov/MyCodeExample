package com.jobc.j112kilo.data.model

import android.content.Context
import com.jobc.j112kilo.R

class CarTypeModel {
    fun getTypeCars(context: Context) : List<TypeOfCar> {
        val listType = mutableListOf<TypeOfCar>()
        listType.add(
            TypeOfCar(
                context.resources.getString(R.string.car_to_250_kg),
                1)
        )
        listType.add(
            TypeOfCar(
                context.resources.getString(R.string.van_car_to_500_kg),
                2)
        )
        listType.add(
            TypeOfCar(
                context.resources.getString(R.string.minibus_to_1000_kg),
                3)
        )
        listType.add(
            TypeOfCar(
                context.resources.getString(R.string.truck_to_1500_kg),
                4)
        )
        listType.add(
            TypeOfCar(
                context.resources.getString(R.string.truck_to_3500_kg),
                5)
        )
        listType.add(
            TypeOfCar(
                context.resources.getString(R.string.truck_to_5000_kg),
                6)
        )
        listType.add(
            TypeOfCar(
                context.resources.getString(R.string.truck_over_5000_kg),
                7)
        )
        return listType
    }
}

data class TypeOfCar (
    val typeCars: String,
    val typeCarsNumber: Int
)
