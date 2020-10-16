package com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model

import com.jobc.j112kilo.data.model.Model

data class RegistrationFormStatePhone(
    val countryNameError: String? = null,
    val typeError: String? = null,
    val phoneError: String? = null,
    val passwordError: String? = null,
    val passwordConfirmError: String? = null,
    val isDataValid: Boolean = false
) : Model