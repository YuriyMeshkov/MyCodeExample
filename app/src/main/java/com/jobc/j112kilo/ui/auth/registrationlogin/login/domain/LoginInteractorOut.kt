package com.jobc.j112kilo.ui.auth.registrationlogin.login.domain

import com.jobc.j112kilo.domain.InteractorOut
import com.jobc.j112kilo.ui.auth.registrationlogin.login.data.model.LoginFormState
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.UserTokenResponseModel

interface LoginInteractorOut : InteractorOut{

    fun onChangeLoginFormState(loginFormState: LoginFormState)
    fun isLoading(loading: Boolean)
    fun onLoaded(userTokenResponseModel: UserTokenResponseModel)
    fun onError(e: Throwable)
}