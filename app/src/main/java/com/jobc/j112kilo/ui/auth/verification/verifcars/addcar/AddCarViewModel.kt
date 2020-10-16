package com.jobc.j112kilo.ui.auth.verification.verifcars.addcar

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jobc.j112kilo.data.model.Car
import com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.data.model.CarFormState
import com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.domain.AddCarInteractor
import com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.domain.AddCarInteractorOut
import com.jobc.j112kilo.utils.SingleLiveEvent
import javax.inject.Inject

class AddCarViewModel @Inject constructor(
    private val interactor: AddCarInteractor
): ViewModel(), AddCarInteractorOut {

    init {
        interactor.setupInteractorOut(this)
    }

    private val carFormStateMut = MutableLiveData<CarFormState>()
    private val loadingMut = MutableLiveData<Boolean>()
    private val errorMut: MutableLiveData<Unit> = SingleLiveEvent()
    private val loadPhotoCarMut = MutableLiveData<Bitmap>()
    private val loadFirstPhotoPassportMut = MutableLiveData<Bitmap>()
    private val loadSecondPhotoPassportMut = MutableLiveData<Bitmap>()

    val carFormState: LiveData<CarFormState> get() = carFormStateMut
    val loading: LiveData<Boolean> get() = loadingMut
    val error: LiveData<Unit> get() = errorMut
    val loadPhotoCar: LiveData<Bitmap> get() = loadPhotoCarMut
    val loadFirstPhotoPassport: LiveData<Bitmap> get() = loadFirstPhotoPassportMut
    val loadSecondPhotoPassport: LiveData<Bitmap> get() = loadSecondPhotoPassportMut

    fun changeDataCar(
        carType: String,
        carModel:String,
        carRegistrationNum: String,
        carPhotoChange: Boolean,
        carPassportChange: Boolean
    ) {
        interactor.addCarDataChanged(
            carType,
            carModel,
            carRegistrationNum,
            carPhotoChange,
            carPassportChange
        )
    }

    fun loadingImage(url: String) {
        interactor.loadingImage(url)
    }

    fun deleteFile(pathFile: String) {
        interactor.deleteFile(pathFile)
    }

    fun saveCarToDatabase(car: Car) {
        interactor.saveCarToDatabase(car)
    }

    override fun onChangeCarFormState(addCarFormState: CarFormState) {
        carFormStateMut.value = addCarFormState
    }

    override fun onLoadedPhotoCar(bitmap: Bitmap) {
        loadPhotoCarMut.value = bitmap
    }

    override fun onLoadedFirsPhotoPassport(bitmap: Bitmap) {
        loadFirstPhotoPassportMut.value = bitmap
    }

    override fun onLoadedSecondPhotoPassport(bitmap: Bitmap) {
        loadSecondPhotoPassportMut.value = bitmap
    }

    override fun onError(e: Throwable) {
        errorMut.value = Unit
    }

    override fun isLoading(loading: Boolean) {
        loadingMut.value = loading
    }
}