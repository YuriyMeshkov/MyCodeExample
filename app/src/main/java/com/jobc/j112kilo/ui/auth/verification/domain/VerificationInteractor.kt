package com.jobc.j112kilo.ui.auth.verification.domain

import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.data.model.UserTokenModel
import com.jobc.j112kilo.domain.Interactor
import com.jobc.j112kilo.ui.auth.verification.data.model.VerificationFormState

interface VerificationInteractor : Interactor<VerificationInteractorOut> {
    fun changeVerificationFormState(verificationFormState: VerificationFormState)
    fun sendingUserDataToServer(user: UserModel, userToken: UserTokenModel)
    fun getParametersForRequest(user: UserModel) : HashMap<String, Any> {
        val parameters = HashMap<String, Any>()
        parameters["name"] = user.userFirstName
        parameters["surname"] = user.userSurName
        parameters["gender"] = user.userGender
        parameters["city"] = user.cityOfResidence
        parameters["document_files"] = getDocumentFiles()
        return parameters
    }

    private fun getDocumentFiles() {
        //
    }
}