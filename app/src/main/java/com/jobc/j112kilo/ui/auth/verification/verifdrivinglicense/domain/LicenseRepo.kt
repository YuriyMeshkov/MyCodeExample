package com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense.domain

import android.graphics.Bitmap
import com.jobc.j112kilo.domain.Repository

interface LicenseRepo : Repository {
    suspend fun loadingImage(pathImage: String) : Bitmap?
}