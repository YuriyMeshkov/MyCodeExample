package com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.domain

import android.graphics.Bitmap
import com.jobc.j112kilo.domain.InteractorOut
import com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.data.model.CarFormState

interface AddCarInteractorOut : InteractorOut {
    fun onChangeCarFormState(addCarFormState: CarFormState)
    fun onLoadedPhotoCar(bitmap: Bitmap)
    fun onLoadedFirsPhotoPassport(bitmap: Bitmap)
    fun onLoadedSecondPhotoPassport(bitmap: Bitmap)
    fun onError(e: Throwable)
    fun isLoading(loading: Boolean)
}