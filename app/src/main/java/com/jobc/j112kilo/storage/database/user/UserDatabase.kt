package com.jobc.j112kilo.storage.database.user

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, exportSchema = false, entities = [UserEntity::class])
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}