package com.jobc.j112kilo.ui.auth.registrationlogin.login.domain

import android.util.Patterns
import com.jobc.j112kilo.domain.Interactor

interface LoginInteractor : Interactor<LoginInteractorOut> {

    fun loginDataChange(
        login: String,
        password: String
    )

    fun loadUserToken(login: String, password: String)

    fun isLoginValid(string: String) : Boolean {
        return when(string.isEmpty() || string[0].toString() == "+") {
            true -> {
                Patterns.PHONE.matcher(string).matches()
            }
            false -> {
                Patterns.EMAIL_ADDRESS.matcher(string).matches()
            }
        }
    }
    fun isPasswordValid(password: String) = password.length > 5
}