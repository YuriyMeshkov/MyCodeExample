package com.jobc.j112kilo.ui.auth.registrationlogin.registration.domain

import android.util.Patterns
import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.domain.Interactor
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.RegistrationModel

interface RegistrationInteractor : Interactor<RegistrationInteractorOut> {

    fun registrationPhoneDataChange(
        country: String,
        typeRole: String,
        phone: String,
        password: String,
        passwordConfirm: String,
    )

    fun registrationAccountDataChange(
        userName: String,
        userSurname: String,
        userGender: String,
        email: String,
        cityResidence: String,
        agreement: Boolean,
        policy: Boolean
    )

    fun registrationUser(registrationModel: RegistrationModel)
    fun registrationAccount(
        token: String,
        name: String,
        surname: String,
        gender: String,
        email: String,
        city: String,
        userAgreement: Boolean,
        privacyPolicy: Boolean
    )
    fun loadUserToken(login: String, password: String)
    fun sendSms(phone: String)
    fun saveToDatabaseUser(user: UserModel)

    fun isLoginValid(str: String) : Boolean =
        if (str.isEmpty() || str[0].toString() != "+") {
            false
        } else {
            Patterns.PHONE.matcher(str).matches()
        }

    fun isPasswordValid(password: String) = password.length > 5
    fun isEnterStringValid(carType: String) = carType.trim().isNotEmpty()
    fun isUserEmailValid(userEmail: String) : Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()
    }
}