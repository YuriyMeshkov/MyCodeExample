package com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model

import com.jobc.j112kilo.data.model.Model

data class RegistrationModel (
    val type: String,
    val password: String,
    val passwordConfirm: String,
    val phone: String,
    val countryName: String,
    val smsCode: String
) : Model