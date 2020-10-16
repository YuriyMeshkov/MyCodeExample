package com.jobc.j112kilo.ui.auth.verification.verifcontacts.domain

import com.jobc.j112kilo.domain.Interactor

interface ContactsInteractor : Interactor<ContactsInteractorOut> {
    fun sendSms(phone: String)
    fun sendEmail(email: String, token: String)
    fun confirmPhone(phone: String, smsCode: String, token: String)
    fun confirmEmail(email: String, emailCode: String, token: String)
}