package com.jobc.j112kilo.ui.auth.verification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.data.model.UserTokenModel
import com.jobc.j112kilo.ui.auth.verification.data.model.VerificationFormState
import com.jobc.j112kilo.ui.auth.verification.domain.VerificationInteractor
import com.jobc.j112kilo.ui.auth.verification.domain.VerificationInteractorOut
import com.jobc.j112kilo.utils.SingleLiveEvent
import javax.inject.Inject

class VerificationViewModel @Inject constructor(
    private val interactor: VerificationInteractor
): ViewModel(), VerificationInteractorOut {

    init {
        interactor.setupInteractorOut(this)
    }

    private val loadingMut = MutableLiveData<Boolean>()
    private val errorMut: MutableLiveData<Unit> = SingleLiveEvent()
    private val verificationFormStateMut = MutableLiveData<VerificationFormState>()

    val loading: LiveData<Boolean> get() = loadingMut
    val error: LiveData<Unit> get() = errorMut
    val verificationFormState: LiveData<VerificationFormState> get() = verificationFormStateMut

    fun changeVerificationFormState(verificationFormState: VerificationFormState) {
        interactor.changeVerificationFormState(verificationFormState)
    }

    fun sendingUserDataToServer(user: UserModel, userToken: UserTokenModel) {
        interactor.sendingUserDataToServer(user, userToken)
    }

    override fun onChangeVerificationFormState(verificationFormState: VerificationFormState) {
        verificationFormStateMut.value = verificationFormState
    }

    override fun isLoading(loading: Boolean) {
        loadingMut.value = loading
    }

    override fun onError(e: Throwable) {
        errorMut.value = Unit
    }

}