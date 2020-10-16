package com.jobc.j112kilo.storage.database.cars

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jobc.j112kilo.data.model.EntityModel

@Entity(tableName = "cars")
data class CarsEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "car_id") val carId: String,
    @ColumnInfo(name = "type") val carType: String,
    @ColumnInfo(name = "model") val carModel: String,
    @ColumnInfo(name = "reg_num") val carRegistrationNum: String,
    @ColumnInfo(name = "photo_car") val pathToCarPhoto: String?,
    @ColumnInfo(name = "photo_change") val carPhotoChange: Boolean,
    @ColumnInfo(name = "photo_first") val pathToPassportPhotoFirst: String?,
    @ColumnInfo(name = "photo_second") val pathToPassportPhotoSecond: String?,
    @ColumnInfo(name = "passport_change") val carPassportChange: Boolean,
    @ColumnInfo(name = "verification") val carVerification: Boolean
) : EntityModel