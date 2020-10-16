package com.jobc.j112kilo.ui.auth.registrationlogin.registration.domain

import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.UserTokenResponseModel
import com.jobc.j112kilo.domain.Repository
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.ResponseToRegistrationAccountModel
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.ResponseToRegistrationModel
import com.jobc.j112kilo.ui.auth.data.model.ResponseToSendSmsModel

interface RegistrationRepo : Repository{
    suspend fun sendSms(parameters: HashMap<String, String>) : ResponseToSendSmsModel
    suspend fun registrationUser(parameters: HashMap<String, String>) : ResponseToRegistrationModel
    suspend fun registrationAccount(
        header: String, parameter:
        HashMap<String, String>
    ) : ResponseToRegistrationAccountModel
    suspend fun loadUserToken(
        login: String,
        password: String,
    ) : UserTokenResponseModel
    suspend fun saveToDatabaseUser(user: UserModel)
}