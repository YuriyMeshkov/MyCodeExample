package com.jobc.j112kilo.ui.auth.verification.verifpersonaldata.data

import android.graphics.Bitmap
import com.jobc.j112kilo.data.RepositoryImpl
import com.jobc.j112kilo.storage.ImageDataSource
import com.jobc.j112kilo.ui.auth.verification.verifpersonaldata.domain.PersonalRepo
import javax.inject.Inject

class PersonalRepoImpl @Inject constructor(
    private val imageDataSource: ImageDataSource
) : RepositoryImpl(), PersonalRepo  {

    override suspend fun loadingImage(pathImage: String): Bitmap?  = ioAsync {
        imageDataSource.loadingImage(pathImage)
    }
}