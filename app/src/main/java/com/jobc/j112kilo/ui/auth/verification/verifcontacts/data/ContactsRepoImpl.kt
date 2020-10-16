package com.jobc.j112kilo.ui.auth.verification.verifcontacts.data

import com.jobc.j112kilo.api.AuthApi
import com.jobc.j112kilo.data.RepositoryImpl
import com.jobc.j112kilo.ui.auth.data.maper.ResponseToSendSmsMapper
import com.jobc.j112kilo.ui.auth.data.model.ResponseToSendSmsModel
import com.jobc.j112kilo.ui.auth.verification.verifcontacts.data.model.ResponseToPhoneOrEmailChange
import com.jobc.j112kilo.ui.auth.verification.verifcontacts.domain.ContactsRepo
import javax.inject.Inject

class ContactsRepoImpl @Inject constructor(
    private val api: AuthApi
) : RepositoryImpl(), ContactsRepo {

    override suspend fun sendSms(
        parameters: HashMap<String, String>,
    ) : ResponseToSendSmsModel = ioAsync {
        val result= api.sendSmsCode(parameters)
        ResponseToSendSmsMapper().map(result)
    }

    override suspend fun sendEmail(
        parameters: HashMap<String, String>,
        token: String
    ) : ResponseToSendSmsModel = ioAsync {
        val result= api.sendSmsForChangeEmail(token, parameters)
        ResponseToSendSmsMapper().map(result)
    }

    override suspend fun confirmPhone(
        parameters: HashMap<String, String>,
        token: String
    ) : ResponseToSendSmsModel = ioAsync {
        val result= api.sendSmsForChangePhone(token, parameters)
        ResponseToSendSmsMapper().map(result)
    }

    override suspend fun confirmEmail(
        parameters: HashMap<String, String>,
        token: String
    ) : ResponseToSendSmsModel = ioAsync {
        val result= api.sendSmsForChangeEmail(token, parameters)
        ResponseToSendSmsMapper().map(result)
    }
}