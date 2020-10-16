package com.jobc.j112kilo.ui.auth.verification.verifcontacts.domain

import com.jobc.j112kilo.domain.Repository
import com.jobc.j112kilo.ui.auth.data.model.ResponseToSendSmsModel
import com.jobc.j112kilo.ui.auth.verification.verifcontacts.data.model.ResponseToPhoneOrEmailChange

interface ContactsRepo : Repository {
    suspend fun sendSms(
        parameters: HashMap<String, String>
    ) : ResponseToSendSmsModel
    suspend fun sendEmail(
        parameters: HashMap<String, String>,
        token: String
    ) : ResponseToSendSmsModel
    suspend fun confirmPhone(
        parameters: HashMap<String, String>,
        token: String
    ) : ResponseToSendSmsModel
    suspend fun confirmEmail(
        parameters: HashMap<String, String>,
        token: String
    ) : ResponseToSendSmsModel
}