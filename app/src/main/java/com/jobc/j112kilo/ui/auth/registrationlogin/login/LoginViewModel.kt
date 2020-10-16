package com.jobc.j112kilo.ui.auth.registrationlogin.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jobc.j112kilo.ui.auth.registrationlogin.login.data.model.LoginFormState
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.UserTokenResponseModel
import com.jobc.j112kilo.ui.auth.registrationlogin.login.domain.LoginInteractor
import com.jobc.j112kilo.ui.auth.registrationlogin.login.domain.LoginInteractorOut
import com.jobc.j112kilo.utils.SingleLiveEvent
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val interactor: LoginInteractor
) : ViewModel(), LoginInteractorOut {

    init {
        interactor.setupInteractorOut(this)
    }

    private val loginFormStateMut = MutableLiveData<LoginFormState>()
    private val loadingMut = MutableLiveData<Boolean>()
    private val errorMut: MutableLiveData<Unit> = SingleLiveEvent()
    private val userTokenMut = MutableLiveData<UserTokenResponseModel>()

    val loginFormState: LiveData<LoginFormState> get() = loginFormStateMut
    val loading: LiveData<Boolean> get() = loadingMut
    val error: LiveData<Unit> get() = errorMut
    val userTokenResponse: LiveData<UserTokenResponseModel> get() = userTokenMut

    fun changeLogin(login: String, password: String) {
        interactor.loginDataChange(login, password)
    }

    fun loginUsedToken(login: String, password: String) {
        interactor.loadUserToken(login, password)
    }

    override fun onChangeLoginFormState(loginFormState: LoginFormState) {
        loginFormStateMut.value = loginFormState
    }

    override fun isLoading(loading: Boolean) {
        loadingMut.value = loading
    }

    override fun onLoaded(userTokenResponseModel: UserTokenResponseModel) {
        userTokenMut.value = userTokenResponseModel
    }

    override fun onError(e: Throwable) {
        errorMut.value = Unit //здесь надо будет переписать
    }
}