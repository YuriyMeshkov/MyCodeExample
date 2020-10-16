package com.jobc.j112kilo.ui.auth.registrationlogin.registration.data

import com.jobc.j112kilo.api.AuthApi

import com.jobc.j112kilo.data.RepositoryImpl
import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.UserTokenResponseModel
import com.jobc.j112kilo.storage.database.user.UserDao
import com.jobc.j112kilo.storage.database.user.UserEntity
import com.jobc.j112kilo.storage.database.usertoken.UserTokenDao
import com.jobc.j112kilo.storage.database.usertoken.UserTokenEntity
import com.jobc.j112kilo.ui.auth.data.maper.ResponseToSendSmsMapper
import com.jobc.j112kilo.ui.auth.data.model.ResponseToSendSmsModel
import com.jobc.j112kilo.ui.auth.registrationlogin.login.data.model.LoginMapperFromResponse
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.mapper.ResponseToRegistrationAccountMapper
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.mapper.ResponseToRegistrationMapper
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.*
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.domain.RegistrationRepo
import com.jobc.j112kilo.utils.phoneNumberStringBuild
import javax.inject.Inject

class RegistrationRepoImpl @Inject constructor(
    private val authApi: AuthApi,
    private val dataBaseUserToken: UserTokenDao,
    private val dataBaseUser: UserDao
): RepositoryImpl(), RegistrationRepo {

    override suspend fun sendSms(parameters: HashMap<String, String>): ResponseToSendSmsModel = ioAsync {
        ResponseToSendSmsMapper().map(authApi.sendSmsCode(parameters))
    }

    override suspend fun registrationUser(
        parameters: HashMap<String, String>
    ) : ResponseToRegistrationModel = ioAsync {
        ResponseToRegistrationMapper().map(authApi.registrationUser(parameters))
    }

    override suspend fun registrationAccount(
        header: String,
            parameter: HashMap<String, String>
    ): ResponseToRegistrationAccountModel = ioAsync {
        ResponseToRegistrationAccountMapper().map(authApi.registrationAccount(header, parameter))
    }

    override suspend fun loadUserToken(
        login: String, password: String
    ) : UserTokenResponseModel = ioAsync {
        val result = authApi.getTokenFromPhone(login, phoneNumberStringBuild(password))
        LoginMapperFromResponse().map(result).apply {
            token?.let {
                dataBaseUserToken.insert(
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

    override suspend fun saveToDatabaseUser(user: UserModel) : Unit = ioAsync {
        with(user) {
            dataBaseUser.insert(
                UserEntity(
                    userRole = role,
                    country = country,
                    userFirstName = userFirstName,
                    userSurName = userSurName,
                    userGender = userGender,
                    cityOfResidence = cityOfResidence,
                    documentApprovalAgreement = documentApprovalAgreement,
                    documentApprovalPolicy = documentApprovalPolicy,
                    userPhone = userPhone,
                    acceptUserPhone = acceptUserPhone,
                    userEmail = userEmail,
                    acceptUserEmail = acceptUserEmail,
                )
            )
        }
    }
}