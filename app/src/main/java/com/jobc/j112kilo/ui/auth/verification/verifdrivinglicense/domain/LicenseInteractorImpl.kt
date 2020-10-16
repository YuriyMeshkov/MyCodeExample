package com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense.domain

import android.content.Context
import com.jobc.j112kilo.R
import com.jobc.j112kilo.domain.InteractorImpl
import com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense.data.model.LicenseFormState
import com.jobc.j112kilo.utils.IMG_DRIVING_LICENSE_FIRST
import javax.inject.Inject

class LicenseInteractorImpl @Inject constructor(
    private val context: Context,
    private val repo: LicenseRepo
) : InteractorImpl<LicenseInteractorOut>(), LicenseInteractor {
    override fun loadingImage(pathImage: String) {
        LaunchSafely(
            {out.isLoading(it)},
            {out.onError(it)}
        ) {
            repo.loadingImage(pathImage)?.let {
                when(pathImage.contains(IMG_DRIVING_LICENSE_FIRST)) {
                    true -> out.onLoadedFirstImg(it)
                    false -> out.onLoadedSecondImg(it)
                }
            }
        }
    }

    override fun changeDataLicense(
        issuedBy: String,
        data: String,
        series: String,
        number: String,
        photoFirst: String,
        photoSecond: String
    ) {
        out.onChangeLicenseFormState(
            if (!isEnterString(issuedBy)) {
                LicenseFormState(
                    issuedByError = context.resources.getString(R.string.invalid_input_field)
                )
            } else if (!isDataString(data)) {
                LicenseFormState(
                    dataError = context.resources.getString(R.string.invalid_date)
                )
            } else if(!isEnterString(series)) {
                LicenseFormState(
                    seriesError = context.resources.getString(R.string.invalid_input_field)
                )
            } else if (!isEnterString(number)) {
                LicenseFormState(
                    numberError = context.resources.getString(R.string.invalid_input_field)
                )
            } else if (!isEnterString(photoFirst)) {
                LicenseFormState()
            } else if (!isEnterString(photoSecond)) {
                LicenseFormState()
            } else {
                LicenseFormState(
                    isDataValid = true
                )
            }
        )
    }
}