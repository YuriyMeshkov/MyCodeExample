package com.jobc.j112kilo.ui.auth.verification.verifcontacts.data.model

data class ResponseToPhoneOrEmailChange(
    val status: Boolean = false,
    val info: String? = null,
    val errors: String? = null
)