package com.jobc.j112kilo.storage.database.cars

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, exportSchema = false, entities = [CarsEntity::class])
abstract class CarsDatabase : RoomDatabase() {
    abstract fun carsDao() : CarsDao
}