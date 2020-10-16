package com.jobc.j112kilo.ui.auth.verification.verifpersonaldata.domain

import android.graphics.Bitmap
import com.jobc.j112kilo.domain.InteractorOut

interface PersonalInteractorOut : InteractorOut {
    fun isLoading(loading: Boolean)
    fun onLoaded(bitmap: Bitmap)
    fun onError(e: Throwable)
}