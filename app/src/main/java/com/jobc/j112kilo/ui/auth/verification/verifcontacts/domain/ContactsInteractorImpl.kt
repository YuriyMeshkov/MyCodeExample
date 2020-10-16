package com.jobc.j112kilo.ui.auth.verification.verifcontacts.domain

import com.jobc.j112kilo.domain.InteractorImpl
import javax.inject.Inject

class ContactsInteractorImpl @Inject constructor(
    private  val repo: ContactsRepo
) : InteractorImpl<ContactsInteractorOut>(), ContactsInteractor {

    override fun sendSms(phone: String) {
        val parameters = HashMap<String, String>()
        parameters["phone"] = phone
        LaunchSafely(
            { out.isLoading(it) },
            { out.onError(it)}
        ) {
            out.sentSms(repo.sendSms(parameters))
        }
    }

    override fun sendEmail(email: String, token: String) {
        val parameters = HashMap<String, String>()
        parameters["email"] = email
        LaunchSafely(
            { out.isLoading(it) },
            { out.onError(it)}
        ) {
            out.sentEmail(repo.sendEmail(parameters, "Bearer $token"))
        }
    }

    override fun confirmPhone(phone: String, smsCode: String, token: String) {
        val parameters = HashMap<String, String>()
        parameters["phone"] = phone
        parameters["smsCode"] = smsCode
        LaunchSafely(
            { out.isLoading(it) },
            { out.onError(it)}
        ) {
            val result = repo.confirmPhone(parameters, "Bearer $token")
            out.changedPhone(result)
        }
    }

    override fun confirmEmail(email: String, emailCode: String, token: String) {
        val parameters = HashMap<String, String>()
        parameters["email"] = email
        parameters["code"] = emailCode
        LaunchSafely(
            { out.isLoading(it) },
            { out.onError(it)}
        ) {
            out.confirmedEmail(repo.confirmEmail(parameters, "Bearer $token"))
        }
    }

}