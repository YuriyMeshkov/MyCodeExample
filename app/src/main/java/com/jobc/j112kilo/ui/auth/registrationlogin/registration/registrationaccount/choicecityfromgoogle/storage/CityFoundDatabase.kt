package com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, exportSchema = false, entities = [CityFoundEntity::class])
abstract class CityFoundDatabase : RoomDatabase() {
    abstract fun cityFoundDao() : CityFoundDao
}