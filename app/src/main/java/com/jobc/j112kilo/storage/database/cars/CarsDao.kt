package com.jobc.j112kilo.storage.database.cars

import androidx.room.Dao
import androidx.room.Query
import com.jobc.j112kilo.storage.database.utils.BaseDao

@Dao
interface CarsDao : BaseDao<CarsEntity> {
    @Query("SELECT *FROM cars")
    fun getCarsFromDatabaseCars() : List<CarsEntity>?
}