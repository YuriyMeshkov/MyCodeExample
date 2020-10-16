package com.jobc.j112kilo.ui.auth.registrationlogin.login.data.model

import com.jobc.j112kilo.data.model.Model

data class LoginFormState(
    val loginError: String? = null,
    val passwordError: String? = null,
    val isDataValid: Boolean = false
) : Model
