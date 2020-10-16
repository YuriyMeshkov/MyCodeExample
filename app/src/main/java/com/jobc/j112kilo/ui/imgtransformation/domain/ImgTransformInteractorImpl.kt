package com.jobc.j112kilo.ui.imgtransformation.domain

import android.content.ContentResolver
import android.graphics.Bitmap
import com.jobc.j112kilo.domain.InteractorImpl
import javax.inject.Inject

class ImgTransformInteractorImpl @Inject constructor(private val repo: ImgTransformRepo) :
    InteractorImpl<ImgTransformInteractorOut>(),
    ImgTransformInteractor {

    override fun writeImage(bitmap: Bitmap, fileName: String, filePath: String) {
        LaunchSafely(
            {out.isWriteOrLoading(it)},
            {out.onError(it)}
        ) {
            repo.writeImage(bitmap, fileName, filePath)
        }
    }

    override fun loadingImage(pathImage: String) {
        LaunchSafely(
            {out.isWriteOrLoading(it)},
            {out.onError(it)}
        ) {
            repo.loadingImage(pathImage)?.let{  out.onLoaded(it) }
        }
    }

    override fun loadingImageFromGallery(
        pathImage: String,
        contentResolver: ContentResolver
    ) {
        LaunchSafely(
            {out.isWriteOrLoading(it)},
            {out.onError(it)}
        ) {
            repo.loadingImageFromGallery(pathImage, contentResolver)?.let { out.onLoaded(it) }
        }
    }
}