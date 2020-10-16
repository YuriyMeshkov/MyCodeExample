package com.jobc.j112kilo.ui.auth.splash.domain

import android.graphics.Bitmap
import com.jobc.j112kilo.data.model.CarsModel
import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.data.model.UserTokenModel

interface SplashRepo {
    suspend fun getUserToken() : UserTokenModel?
    suspend fun getUserData() : UserModel?
    suspend fun getCarsData() : CarsModel?
    suspend fun loadingImage(pathImage: String): Bitmap?
}