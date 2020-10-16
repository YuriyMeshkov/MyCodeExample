package com.jobc.j112kilo.storage.database.usertoken

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, exportSchema = false, entities = [UserTokenEntity::class])
abstract class UserTokenDatabase : RoomDatabase() {
    abstract fun userTokenDao() : UserTokenDao
}