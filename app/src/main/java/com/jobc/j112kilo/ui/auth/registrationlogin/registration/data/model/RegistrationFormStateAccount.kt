package com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model

import com.jobc.j112kilo.data.model.Model

data class RegistrationFormStateAccount(
    val usernameError: String? = null,
    val userSurnameError: String? = null,
    val userGenderError: String? = null,
    val emailError: String? = null,
    val cityResidenceError: String? = null,
    var agreementError: String? = null,
    var policyError: String? = null,
    val isDataValid: Boolean = false
) : Model