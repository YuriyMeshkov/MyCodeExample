package com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_found")
data class CityFoundEntity (
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "city_name") val cityName: String,
    @ColumnInfo(name = "city_region") val cityRegion: String,
    @ColumnInfo(name = "city_lat") val cityLat: Double,
    @ColumnInfo(name ="city_lng") val cityLng: Double

)