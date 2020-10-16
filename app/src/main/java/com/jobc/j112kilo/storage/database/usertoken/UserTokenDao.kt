package com.jobc.j112kilo.storage.database.usertoken

import androidx.room.Dao
import androidx.room.Query
import com.jobc.j112kilo.storage.database.utils.BaseDao

@Dao
interface UserTokenDao : BaseDao<UserTokenEntity> {

    @Query("SELECT * FROM user_token WHERE  id = 1")
    fun getFromDataBaseUserToken() : UserTokenEntity?
}