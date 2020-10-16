package com.jobc.j112kilo.ui.auth.verification.verifpersonaldata.domain

import com.jobc.j112kilo.domain.Interactor

interface PersonalInteractor : Interactor<PersonalInteractorOut> {
    fun loadingImage(pathImage: String)
}