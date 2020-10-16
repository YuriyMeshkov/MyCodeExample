package com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.domain
import android.graphics.Bitmap
import com.jobc.j112kilo.data.model.Car
import com.jobc.j112kilo.domain.Repository

interface AddCarRepo : Repository {
    suspend fun loadingImage(pathImage: String) : Bitmap?
    suspend fun deleteFile(pathFile: String)
    suspend fun saveCarToDatabase(car: Car)
}