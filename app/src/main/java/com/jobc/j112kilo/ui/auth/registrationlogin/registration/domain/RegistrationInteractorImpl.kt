package com.jobc.j112kilo.ui.auth.registrationlogin.registration.domain

import android.content.Context
import com.jobc.j112kilo.R
import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.domain.InteractorImpl
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.RegistrationFormStateAccount
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.RegistrationFormStatePhone
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.RegistrationModel
import javax.inject.Inject

class RegistrationInteractorImpl @Inject constructor(
    private val context: Context,
    private val repo: RegistrationRepo
) : InteractorImpl<RegistrationInteractorOut>(), RegistrationInteractor {

    override fun registrationPhoneDataChange(
        country: String,
        typeRole: String,
        phone: String,
        password: String,
        passwordConfirm: String
    ) {
        out.onChangeRegistrationFormStatePhone(
            if (country == context.resources.getString(R.string.choice_country) ) {
                RegistrationFormStatePhone(
                    countryNameError = context.resources.getString(R.string.invalid_choice_country)
                )
            } else if (typeRole == context.resources.getString(R.string.choice_role)) {
                RegistrationFormStatePhone(
                    typeError = context.resources.getString(R.string.invalid_choice_type_role)
                )
            } else if (!isLoginValid(phone)) {
                RegistrationFormStatePhone(
                    phoneError = context.resources.getString(R.string.invalid_userphone)
                )
            } /*else if (!isPasswordValid(password)) {
                RegistrationFormStatePhone(
                    passwordError = context.resources.getString(R.string.invalid_password)
                )
            } else if(passwordConfirm != password) {
                RegistrationFormStatePhone(
                    passwordConfirmError = context.resources.getString(R.string.invalid_confirm_password)
                )
            }*/ else {
                RegistrationFormStatePhone(
                    isDataValid = true
                )
            }
        )
    }

    override fun registrationAccountDataChange(
        userName: String,
        userSurname: String,
        userGender: String,
        email: String,
        cityResidence: String,
        agreement: Boolean,
        policy: Boolean
    ) {
        out.onChangeRegistrationFormStateAccount(
            if(!isEnterStringValid(userName)) {
                RegistrationFormStateAccount(
                    usernameError = context.resources.getString(R.string.invalid_input_field)
                )
            } else if(!isEnterStringValid(userSurname)) {
                RegistrationFormStateAccount(
                    userSurnameError = context.resources.getString(R.string.invalid_input_field)
                )
            } else if(userGender.isEmpty()) {
                RegistrationFormStateAccount(
                    userGenderError = context.resources.getString(R.string.invalid_gender)
                )
            } else if(!isUserEmailValid(email)) {
                RegistrationFormStateAccount(
                    emailError = context.resources.getString(R.string.email_error)
                )
            } else if(!isEnterStringValid(cityResidence)) {
                RegistrationFormStateAccount(
                    cityResidenceError = context.resources.getString(R.string.invalid_input_field)
                )
            } else if(!agreement) {
                RegistrationFormStateAccount(
                    agreementError = context.resources.getString(R.string.invalid_approval_agreement)
                )
            } else if(!policy) {
                RegistrationFormStateAccount(
                    policyError = context.resources.getString(R.string.invalid_approval_policy)
                )
            } else {
                RegistrationFormStateAccount(
                    isDataValid = true
                )
            }
        )
    }

    override fun registrationUser(registrationModel: RegistrationModel) {
        val parameters = HashMap<String, String>()
        parameters["type"] = registrationModel.type
        parameters["password"] = registrationModel.password
        parameters["passwordConfirm"] = registrationModel.passwordConfirm
        parameters["phone"] = registrationModel.phone
        parameters["countryName"] = registrationModel.countryName
        parameters["smsCode"] = registrationModel.smsCode
        LaunchSafely(
            { out.isLoading(it) },
            { out.onError(it) }
        ) {
            out.onRegistered(repo.registrationUser(parameters))
        }
    }

    override fun registrationAccount(
        token: String,
        name: String,
        surname: String,
        gender: String,
        email: String,
        city: String,
        userAgreement: Boolean,
        privacyPolicy: Boolean
    ) {
        val parameters = HashMap<String, String>()
        parameters["name"] = name
        parameters["surname"] = surname
        parameters["gender"] = gender
        parameters["email"] = email
        parameters["city"] = city
        parameters["userAgreement"] = when(userAgreement) {
            true -> "1"
            false -> "0"
        }
        parameters["privacyPolicy"] = when(privacyPolicy) {
            true -> "1"
            false -> "0"
        }
        LaunchSafely(
            { out.isLoading(it) },
            { out.onError(it)}
        ) {
            out.onRegisteredAccount(repo.registrationAccount("Bearer $token", parameters))
        }
    }

    override fun loadUserToken(login: String, password: String) {
        LaunchSafely(
            { out.isLoading(it) },
            { out.onError(it)}
        ) {
            out.onLoaded(repo.loadUserToken(login, password))
        }
    }

    override fun sendSms(phone: String) {
        val parameter = HashMap<String, String>()
        parameter["phone"] = phone
        LaunchSafely(
            { out.isLoading(it) },
            { out.onError(it)}
        ) {
            out.sentSms(repo.sendSms(parameter))
        }
    }

    override fun saveToDatabaseUser(user: UserModel) {
        LaunchSafely(
            { out.isLoading(it) },
            { out.onError(it) }
        ){
            repo.saveToDatabaseUser(user)
        }

    }


}