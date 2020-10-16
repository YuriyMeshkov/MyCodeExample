package com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model

import com.jobc.j112kilo.data.model.Model

data class ResponseToRegistrationAccountModel(
    val status: Boolean = false,
    val info: String? = null,
    val errors: String? = null
) : Model