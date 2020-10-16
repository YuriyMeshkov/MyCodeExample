package com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.domain


import android.content.Context
import com.jobc.j112kilo.R
import com.jobc.j112kilo.data.model.Car
import com.jobc.j112kilo.domain.InteractorImpl
import com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.data.model.CarFormState
import com.jobc.j112kilo.utils.IMG_CAR
import com.jobc.j112kilo.utils.IMG_TECHNICAL_PASSPORT_FIRS
import com.jobc.j112kilo.utils.IMG_TECHNICAL_PASSPORT_SECOND
import javax.inject.Inject

class AddCarInteractorImpl @Inject constructor(
    private val context: Context,
    private val repo: AddCarRepo
) : InteractorImpl<AddCarInteractorOut>(), AddCarInteractor {

    override fun addCarDataChanged(
        carType: String,
        carModel: String,
        carRegistrationNum: String,
        carPhotoChange: Boolean,
        carPassportChange: Boolean
    ) {
        out.onChangeCarFormState(
            if (carType == context.resources.getString(R.string.car_type)) {
                CarFormState(
                    carTypeError = context.resources.getString(R.string.invalid_car_type)
                )
            } else if (!isEnterString(carModel)) {
                CarFormState(
                    carModelError = context.resources.getString(R.string.invalid_car_model)
                )
            } else if (!isEnterString(carRegistrationNum)) {
                CarFormState(
                    carRegistrationNumError = context.resources
                        .getString(R.string.invalid_car_registration_number)
                )
            } else if (!carPhotoChange) {
                CarFormState(
                    carPhotoChangeError = carPhotoChange
                )
            } else if (!carPassportChange) {
                CarFormState(
                    carPassportChangeError = carPassportChange
                )
            } else {
                CarFormState(
                    isDataValid = true
                )
            }
        )
    }

    override fun loadingImage(pathImage: String) {
        LaunchSafely(
            {out.isLoading(it)},
            {out.onError(it)}
        ) {
            repo.loadingImage(pathImage)?.let {
                when {
                    pathImage.contains(IMG_CAR) -> out.onLoadedPhotoCar(it)
                    pathImage.contains(IMG_TECHNICAL_PASSPORT_FIRS) -> {
                        out.onLoadedFirsPhotoPassport(it)
                    }
                    pathImage.contains(IMG_TECHNICAL_PASSPORT_SECOND) -> {
                        out.onLoadedSecondPhotoPassport(it)
                    }
                }
            }
        }
    }

    override fun deleteFile(pathFile: String) {
        LaunchSafely(
            {out.isLoading(it)},
            {out.onError(it)}
        ) {
            repo.deleteFile(pathFile)
        }
    }

    override fun saveCarToDatabase(car: Car) {
        LaunchSafely(
            {out.isLoading(it)},
            {out.onError(it)}
        ) {
            repo.saveCarToDatabase(car)
        }
    }
}