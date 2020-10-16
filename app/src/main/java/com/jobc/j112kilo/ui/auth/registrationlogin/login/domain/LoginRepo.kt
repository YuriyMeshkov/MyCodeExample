package com.jobc.j112kilo.ui.auth.registrationlogin.login.domain

import com.jobc.j112kilo.domain.Repository
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.UserTokenResponseModel

interface LoginRepo : Repository {
    suspend fun loadUserToken(
        login: String,
        password: String,
        passwordPhone: Boolean
    ) : UserTokenResponseModel
}