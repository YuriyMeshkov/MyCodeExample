package com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model

import com.jobc.j112kilo.data.model.Model

data class ResponseToRegistrationModel(
    val success: Boolean = false,
    val errors: String? = null
) : Model