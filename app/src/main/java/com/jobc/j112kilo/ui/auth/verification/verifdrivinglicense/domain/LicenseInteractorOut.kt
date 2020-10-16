package com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense.domain

import android.graphics.Bitmap
import com.jobc.j112kilo.domain.InteractorOut
import com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense.data.model.LicenseFormState

interface LicenseInteractorOut : InteractorOut {
    fun isLoading(loading: Boolean)
    fun onLoadedFirstImg(bitmap: Bitmap)
    fun onLoadedSecondImg(bitmap: Bitmap)
    fun onError(e: Throwable)
    fun onChangeLicenseFormState(licenseFormState: LicenseFormState)
}