package com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense.data

import android.graphics.Bitmap
import com.jobc.j112kilo.data.RepositoryImpl
import com.jobc.j112kilo.storage.ImageDataSource
import com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense.domain.LicenseRepo
import javax.inject.Inject

class LicenseRepoImpl @Inject constructor(
    private val imageDataSource: ImageDataSource
) : RepositoryImpl(), LicenseRepo {
    override suspend fun loadingImage(pathImage: String): Bitmap? = ioAsync {
        imageDataSource.loadingImage(pathImage)
    }

}