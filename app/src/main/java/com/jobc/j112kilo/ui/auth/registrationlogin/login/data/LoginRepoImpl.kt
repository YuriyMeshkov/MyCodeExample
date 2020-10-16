package com.jobc.j112kilo.ui.auth.registrationlogin.login.data

import com.jobc.j112kilo.api.AuthApi
import com.jobc.j112kilo.data.RepositoryImpl
import com.jobc.j112kilo.ui.auth.registrationlogin.login.data.model.LoginMapperFromResponse
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.UserTokenResponseModel
import com.jobc.j112kilo.storage.database.usertoken.UserTokenDao
import com.jobc.j112kilo.storage.database.usertoken.UserTokenEntity
import com.jobc.j112kilo.ui.auth.registrationlogin.login.domain.LoginRepo
import com.jobc.j112kilo.utils.phoneNumberStringBuild
import javax.inject.Inject

class LoginRepoImpl @Inject constructor(
    private val authApi: AuthApi,
    private val dataBase: UserTokenDao
) : RepositoryImpl(), LoginRepo{

    override suspend fun loadUserToken(
        login: String, password: String, passwordPhone: Boolean
    ) : UserTokenResponseModel = ioAsync {
        val result =
            when(passwordPhone) {
                true -> authApi.getTokenFromPhone(login, phoneNumberStringBuild(password))
                false -> authApi.getTokenFromEmail(login, password)
            }
        LoginMapperFromResponse().map(result).apply {
            token?.let {
                dataBase.insert(
                    UserTokenEntity(
                        token = it,
                        verified = verified,
                        userId = userId,
                        role = role,
                        deviceId = deviceId
                    )
                )
            }
        }
    }
}