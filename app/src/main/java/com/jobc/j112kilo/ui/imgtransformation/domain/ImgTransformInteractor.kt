package com.jobc.j112kilo.ui.imgtransformation.domain

import android.content.ContentResolver
import android.graphics.Bitmap
import com.jobc.j112kilo.domain.Interactor

interface ImgTransformInteractor : Interactor<ImgTransformInteractorOut> {
    fun writeImage(bitmap: Bitmap, fileName: String, filePath: String)
    fun loadingImage (pathImage: String)
    fun loadingImageFromGallery(
        pathImage: String,
        contentResolver: ContentResolver
    )
}