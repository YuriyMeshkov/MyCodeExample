package com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.storage

import androidx.room.Dao
import androidx.room.Query
import com.jobc.j112kilo.storage.database.utils.BaseDao

@Dao
interface CityFoundDao : BaseDao<CityFoundEntity> {
    @Query("SELECT * FROM city_found")
    fun getCityFoundFromDatabase() : List<CityFoundEntity>
}