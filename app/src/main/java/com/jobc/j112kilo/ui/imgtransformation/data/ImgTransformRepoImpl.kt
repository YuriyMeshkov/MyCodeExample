package com.jobc.j112kilo.ui.imgtransformation.data

import android.content.ContentResolver
import android.graphics.Bitmap
import com.jobc.j112kilo.data.RepositoryImpl
import com.jobc.j112kilo.ui.imgtransformation.domain.ImgTransformRepo
import com.jobc.j112kilo.storage.ImageDataSource
import javax.inject.Inject

class ImgTransformRepoImpl @Inject constructor(private val imageDataSource: ImageDataSource) :
    RepositoryImpl(), ImgTransformRepo {

    override suspend fun writeImage(bitmap: Bitmap, fileName: String, filePath: String) {
        imageDataSource.writeImage(bitmap, fileName, filePath)
        //***********************************************************************************************
        //здесь необходимо  отправить данные на сайт
    }

    override suspend fun loadingImage(pathImage: String): Bitmap = ioAsync {
        imageDataSource.loadingImage(pathImage)?.apply {  }
    }

    override suspend fun loadingImageFromGallery(
        pathImage: String,
        contentResolver: ContentResolver
    ): Bitmap = ioAsync {
        imageDataSource.loadingImageFromGallery(pathImage, contentResolver)
    }

}