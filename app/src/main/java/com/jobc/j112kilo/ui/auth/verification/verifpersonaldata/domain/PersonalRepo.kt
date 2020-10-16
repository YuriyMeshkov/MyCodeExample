package com.jobc.j112kilo.ui.auth.verification.verifpersonaldata.domain

import android.graphics.Bitmap
import com.jobc.j112kilo.domain.Repository

interface PersonalRepo : Repository {
    suspend fun loadingImage(pathImage: String) : Bitmap?
}