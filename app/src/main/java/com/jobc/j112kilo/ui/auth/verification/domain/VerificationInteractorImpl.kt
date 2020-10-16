package com.jobc.j112kilo.ui.auth.verification.domain

import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.data.model.UserTokenModel
import com.jobc.j112kilo.domain.InteractorImpl
import com.jobc.j112kilo.ui.auth.verification.data.model.VerificationFormState
import javax.inject.Inject

class VerificationInteractorImpl @Inject constructor(
    private val repo: VerificationRepo
) : InteractorImpl<VerificationInteractorOut>(), VerificationInteractor {

    override fun changeVerificationFormState(verificationFormState: VerificationFormState) {
        out.onChangeVerificationFormState(
            when(verificationFormState.contactsDataError
                    && verificationFormState.personalDataError
                    && verificationFormState.drivingLicenseError
                    && verificationFormState.carsError) {
                true -> {
                    VerificationFormState(
                        isDataValid = true
                    )
                }
                false  -> {
                    VerificationFormState(
                        isDataValid = false
                    )
                }
            }
        )
    }

    override fun sendingUserDataToServer(user: UserModel, userToken: UserTokenModel) {
        val parameters = HashMap<String, String>()
        val token = "Bearer ${userToken.token}"
        LaunchSafely(
            { out.isLoading(it) },
            { out.onError(it) }
        ) {
            repo.sendingUserDataToServer(user, userToken,token, parameters)
        }
    }
}