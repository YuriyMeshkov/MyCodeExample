package com.jobc.j112kilo.ui.auth.registrationlogin.registration.domain

import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.UserTokenResponseModel
import com.jobc.j112kilo.domain.InteractorOut
import com.jobc.j112kilo.ui.auth.data.model.ResponseToSendSmsModel
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.*

interface RegistrationInteractorOut : InteractorOut {

    fun onChangeRegistrationFormStatePhone(registrationFormStatePhone: RegistrationFormStatePhone)
    fun onChangeRegistrationFormStateAccount(
        registrationFormStateAccount: RegistrationFormStateAccount
    )
    fun isLoading(loading: Boolean)
    fun onRegistered(registered: ResponseToRegistrationModel)
    fun onLoaded(userTokenResponseModel: UserTokenResponseModel)
    fun onError(e: Throwable)
    fun sentSms(sentSmsRegistrationToSendSms: ResponseToSendSmsModel)
    fun onRegisteredAccount(responseToRegistrationAccountModel: ResponseToRegistrationAccountModel)
}