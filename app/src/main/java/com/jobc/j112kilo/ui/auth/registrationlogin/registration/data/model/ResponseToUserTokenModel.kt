package com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model

import com.jobc.j112kilo.data.model.Model


class UserTokenResponseModel(
    val token: String? = null,
    val errors: ErrorModel? = null,
    val verified: Boolean = false,
    val userId: Int = 0,
    val role: String = "",
    val deviceId: String? = null
) : Model

data class ErrorModel(
    val attribute: String = "",
    val label: String = "",
    val error: List<String>? = null
)