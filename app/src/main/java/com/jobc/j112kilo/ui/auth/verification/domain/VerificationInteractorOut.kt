package com.jobc.j112kilo.ui.auth.verification.domain

import com.jobc.j112kilo.domain.InteractorOut
import com.jobc.j112kilo.ui.auth.verification.data.model.VerificationFormState

interface VerificationInteractorOut : InteractorOut {
    fun onChangeVerificationFormState(verificationFormState: VerificationFormState)
    fun isLoading(loading: Boolean)
    fun onError(e: Throwable)
    //fun sentUserDataToServer()
}