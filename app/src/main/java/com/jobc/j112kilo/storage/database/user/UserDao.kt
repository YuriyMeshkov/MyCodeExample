package com.jobc.j112kilo.storage.database.user

import androidx.room.Dao
import androidx.room.Query
import com.jobc.j112kilo.storage.database.utils.BaseDao

@Dao
interface UserDao : BaseDao<UserEntity> {
    @Query("SELECT * FROM user WHERE id = 1")
    fun getFromDataBaseUser() : UserEntity?
}