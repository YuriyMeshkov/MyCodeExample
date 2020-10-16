package com.jobc.j112kilo.ui.auth.registrationlogin.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.UserTokenResponseModel
import com.jobc.j112kilo.ui.auth.data.model.ResponseToSendSmsModel
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.*
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.domain.RegistrationInteractor
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.domain.RegistrationInteractorOut
import com.jobc.j112kilo.utils.SingleLiveEvent
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val interactor: RegistrationInteractor
): ViewModel(), RegistrationInteractorOut {

    init {
        interactor.setupInteractorOut(this)
    }

    private val registrationFormStatePhoneMut = MutableLiveData<RegistrationFormStatePhone>()
    private val registrationFormStateAccountMut = MutableLiveData<RegistrationFormStateAccount>()
    private val registeredMut: MutableLiveData<ResponseToRegistrationModel> = SingleLiveEvent()
    private val loadingMut = MutableLiveData<Boolean>()
    private val errorMut: MutableLiveData<Unit> = SingleLiveEvent()
    private val userTokenMut: MutableLiveData<UserTokenResponseModel> = SingleLiveEvent()
    private val sendSmsMut: MutableLiveData<ResponseToSendSmsModel> = SingleLiveEvent()
    private val registeredAccountMut = MutableLiveData<ResponseToRegistrationAccountModel>()

    val registrationFormStatePhone: LiveData<RegistrationFormStatePhone>
        get() = registrationFormStatePhoneMut
    val registrationFormStateAccount: LiveData<RegistrationFormStateAccount>
        get() = registrationFormStateAccountMut
    val registered: LiveData<ResponseToRegistrationModel> get() = registeredMut
    val loading: LiveData<Boolean> get() = loadingMut
    val error: LiveData<Unit> get() = errorMut
    val userTokenResponse: LiveData<UserTokenResponseModel> get() = userTokenMut
    val sendSms: LiveData<ResponseToSendSmsModel> get() = sendSmsMut
    val toRegistrationAccount: LiveData<ResponseToRegistrationAccountModel> get() = registeredAccountMut

    fun changeDataRegistrationPhone(
        country: String,
        typeRole: String,
        phone: String,
        password: String,
        passwordConfirm: String
    ) {
        interactor.registrationPhoneDataChange(
            country,
            typeRole,
            phone,
            password,
            passwordConfirm
        )
    }
    fun changeDataRegistrationAccount(
        userName: String,
        userSurname: String,
        userGender: String,
        email: String,
        cityResidence: String,
        agreement: Boolean,
        policy: Boolean,
    ) {
        interactor.registrationAccountDataChange(
            userName,
            userSurname,
            userGender,
            email,
            cityResidence,
            agreement,
            policy
        )
    }

    fun sendSmsCode(phone: String) {
        interactor.sendSms(phone)
    }

    fun registrationUser(registrationModel: RegistrationModel) {
        interactor.registrationUser(registrationModel)
    }

    fun registrationAccount(
        token: String,
        name: String,
        surname: String,
        gender: String,
        email: String,
        city: String,
        userAgreement: Boolean,
        privacyPolicy: Boolean
    ) {
        interactor.registrationAccount(
            token,
            name,
            surname,
            gender,
            email,
            city,
            userAgreement,
            privacyPolicy
        )
    }

    fun loginUsedToken(login: String, password: String) {
        interactor.loadUserToken(login, password)
    }

    fun saveToDatabaseUser(user: UserModel) {
        interactor.saveToDatabaseUser(user)
    }

    override fun onChangeRegistrationFormStatePhone(registrationFormStatePhone: RegistrationFormStatePhone) {
        registrationFormStatePhoneMut.value = registrationFormStatePhone
    }

    override fun onChangeRegistrationFormStateAccount(registrationFormStateAccount: RegistrationFormStateAccount) {
        registrationFormStateAccountMut.value = registrationFormStateAccount
    }

    override fun isLoading(loading: Boolean) {
        loadingMut.value = loading
    }

    override fun onRegistered(registered: ResponseToRegistrationModel) {
        registeredMut.value = registered
    }

    override fun onLoaded(userTokenResponseModel: UserTokenResponseModel) {
        userTokenMut.value = userTokenResponseModel
    }

    override fun onError(e: Throwable) {
        errorMut.value = Unit
    }

    override fun sentSms(sentSmsRegistrationToSendSms: ResponseToSendSmsModel) {
        sendSmsMut.value = sentSmsRegistrationToSendSms
    }

    override fun onRegisteredAccount(responseToRegistrationAccountModel: ResponseToRegistrationAccountModel) {
        registeredAccountMut.value = responseToRegistrationAccountModel
    }

}