package com.jobc.j112kilo.storage.database.usertoken

import androidx.room.*
import com.jobc.j112kilo.data.model.EntityModel
import com.jobc.j112kilo.storage.database.utils.ConverterBoolean

@Entity(tableName = "user_token")
data class UserTokenEntity (
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    val id: Int = 1,

    @ColumnInfo(name = "token")
    val token: String,

    @ColumnInfo(name = "verified")
    @TypeConverters(ConverterBoolean::class)
    val verified: Boolean,

    @ColumnInfo(name = "user_id")
    val userId: Int,

    @ColumnInfo(name = "role")
    val role: String,

    @ColumnInfo(name = "device_id")
    val deviceId: String?
) : EntityModel
