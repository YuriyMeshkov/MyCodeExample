package com.jobc.j112kilo.ui.imgtransformation.domain

import android.content.ContentResolver
import android.graphics.Bitmap
import com.jobc.j112kilo.domain.Repository

interface ImgTransformRepo : Repository {
    suspend fun writeImage(bitmap: Bitmap, fileName: String, filePath: String)
    suspend fun loadingImage (pathImage: String) : Bitmap?
    suspend fun loadingImageFromGallery(
        pathImage: String,
        contentResolver: ContentResolver
    ): Bitmap?
}