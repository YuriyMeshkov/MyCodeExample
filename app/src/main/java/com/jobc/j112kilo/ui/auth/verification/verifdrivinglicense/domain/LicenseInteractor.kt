package com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense.domain

import com.jobc.j112kilo.domain.Interactor

interface LicenseInteractor : Interactor<LicenseInteractorOut> {
    fun loadingImage(pathImage: String)
    fun changeDataLicense(
        issuedBy: String,
        data: String,
        series: String,
        number: String,
        photoFirst: String,
        photoSecond: String
    )
    fun isEnterString(carType: String) = carType.trim().isNotEmpty()
    fun isDataString(data: String) = data.trim().length > 9
}