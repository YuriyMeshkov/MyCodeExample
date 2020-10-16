package com.jobc.j112kilo.ui.imgtransformation.domain

import android.graphics.Bitmap
import com.jobc.j112kilo.domain.InteractorOut

interface ImgTransformInteractorOut : InteractorOut {

    fun isWriteOrLoading(loading: Boolean)
    fun onError(e: Throwable)
    fun onLoaded(bitmap: Bitmap)
}