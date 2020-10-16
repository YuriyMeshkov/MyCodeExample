package com.jobc.j112kilo.ui.auth.verification.verifcontacts.domain

import com.jobc.j112kilo.domain.InteractorOut
import com.jobc.j112kilo.ui.auth.data.model.ResponseToSendSmsModel
import com.jobc.j112kilo.ui.auth.verification.verifcontacts.data.model.ResponseToPhoneOrEmailChange

interface ContactsInteractorOut : InteractorOut {
    fun onError(e: Throwable)
    fun isLoading(loading: Boolean)
    fun sentSms(responseToSendSmsModel: ResponseToSendSmsModel)
    fun sentEmail(responseToSendSmsModel: ResponseToSendSmsModel)
    fun changedPhone(responseToSendSmsModel: ResponseToSendSmsModel)
    fun confirmedEmail(responseToSendSmsModel: ResponseToSendSmsModel)
}