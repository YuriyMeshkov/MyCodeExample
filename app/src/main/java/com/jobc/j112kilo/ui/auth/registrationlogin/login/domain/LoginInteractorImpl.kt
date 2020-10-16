package com.jobc.j112kilo.ui.auth.registrationlogin.login.domain

import android.content.Context
import com.jobc.j112kilo.R
import com.jobc.j112kilo.domain.InteractorImpl
import com.jobc.j112kilo.ui.auth.registrationlogin.login.data.model.LoginFormState
import javax.inject.Inject

class LoginInteractorImpl @Inject constructor(
    private val context: Context,
    private val repo: LoginRepo
) : InteractorImpl<LoginInteractorOut>(), LoginInteractor {

    private var passwordPhone = true

    override fun loginDataChange(login: String, password: String) {
        out.onChangeLoginFormState(
            if (!isLoginValid(login)) {
                when(login.isEmpty() || login[0].toString() == "+") {
                    true -> {
                        LoginFormState(
                            loginError = context.resources.getString(R.string.invalid_userphone)
                        )
                    }
                    false -> {
                        passwordPhone = false
                        LoginFormState(
                            loginError = context.resources.getString(R.string.invalid_user_email)
                        )
                    }
                }
            } else if (!isPasswordValid(password)) {
                LoginFormState(
                    passwordError = context.resources.getString(R.string.invalid_password)
                )
            } else {
                LoginFormState(
                    isDataValid = true
                )
            }
        )
    }

    override fun loadUserToken(login: String, password: String) {
        LaunchSafely(
            { out.isLoading(it) },
            { out.onError(it) }
        ) {

            out.onLoaded(repo.loadUserToken(login, password, passwordPhone))
        }
    }

}