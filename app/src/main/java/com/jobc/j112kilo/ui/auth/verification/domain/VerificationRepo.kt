package com.jobc.j112kilo.ui.auth.verification.domain

import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.data.model.UserTokenModel
import com.jobc.j112kilo.domain.Repository

interface VerificationRepo : Repository {
    suspend fun sendingUserDataToServer(
        user: UserModel,
        userToken: UserTokenModel,
        token: String,
        parameters: HashMap<String, String>
    )

    suspend fun saveToDatabaseUserToken(userToken: UserTokenModel)
}