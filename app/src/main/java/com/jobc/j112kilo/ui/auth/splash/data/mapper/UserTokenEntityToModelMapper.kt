package com.jobc.j112kilo.ui.auth.splash.data.mapper

import com.jobc.j112kilo.data.mapper.MapperFromDatabase
import com.jobc.j112kilo.data.model.UserTokenModel
import com.jobc.j112kilo.storage.database.usertoken.UserTokenEntity


class UserTokenEntityToModelMapper: MapperFromDatabase<UserTokenEntity, UserTokenModel>() {
    override fun map(from: UserTokenEntity): UserTokenModel {
        return UserTokenModel(
            token = from.token,
            verified = from.verified,
            userId = from.userId,
            role = from.role,
            deviceId = from.deviceId
        )
    }
}