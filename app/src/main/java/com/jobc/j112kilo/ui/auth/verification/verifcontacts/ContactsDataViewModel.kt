package com.jobc.j112kilo.ui.auth.verification.verifcontacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jobc.j112kilo.ui.auth.data.model.ResponseToSendSmsModel
import com.jobc.j112kilo.ui.auth.verification.verifcontacts.domain.ContactsInteractor
import com.jobc.j112kilo.ui.auth.verification.verifcontacts.domain.ContactsInteractorOut
import com.jobc.j112kilo.utils.SingleLiveEvent
import javax.inject.Inject

class ContactsDataViewModel @Inject constructor(
    private val interactor: ContactsInteractor
) : ViewModel(), ContactsInteractorOut {

    init {
        interactor.setupInteractorOut(this)
    }

    private val loadingMut = MutableLiveData<Boolean>()
    private val errorMut: MutableLiveData<Unit> = SingleLiveEvent()
    private val smsCodeMut: MutableLiveData<ResponseToSendSmsModel> = SingleLiveEvent()
    private val emailCodeMut: MutableLiveData<ResponseToSendSmsModel> = SingleLiveEvent()
    private val confirmedEmailMut =  MutableLiveData<ResponseToSendSmsModel>()
    private val confirmedPhoneMut =  MutableLiveData<ResponseToSendSmsModel>()

    val loading: LiveData<Boolean> get() = loadingMut
    val error: LiveData<Unit> get() = errorMut
    val smsCode: LiveData<ResponseToSendSmsModel> get() = smsCodeMut
    val emailCode: LiveData<ResponseToSendSmsModel> get() = emailCodeMut
    val confirmedEmail: LiveData<ResponseToSendSmsModel> get() = confirmedEmailMut
    val confirmedPhone: LiveData<ResponseToSendSmsModel> get() = confirmedPhoneMut


    fun sendSms(phone: String) {
        interactor.sendSms(phone)
    }

    fun sendEmail(email: String, token: String) {
        interactor.sendEmail(email, token)
    }

    fun confirmEmail(email: String, emailCode: String, token: String) {
        interactor.confirmEmail(email, emailCode, token)
    }
    fun confirmPhone(phone: String, smsCode: String, token: String) {
        interactor.confirmPhone(phone, smsCode, token)
    }

    override fun sentSms(responseToSendSmsModel: ResponseToSendSmsModel) {
        smsCodeMut.value = responseToSendSmsModel
    }

    override fun sentEmail(responseToSendSmsModel: ResponseToSendSmsModel) {
        emailCodeMut.value = responseToSendSmsModel
    }

    override fun changedPhone(responseToSendSmsModel: ResponseToSendSmsModel) {
        confirmedPhoneMut.value = responseToSendSmsModel
    }

    override fun confirmedEmail(responseToSendSmsModel: ResponseToSendSmsModel) {
        confirmedEmailMut.value = responseToSendSmsModel
    }

    override fun isLoading(loading: Boolean) {
        loadingMut.value = loading
    }

    override fun onError(e: Throwable) {
        errorMut.value = Unit
    }

}