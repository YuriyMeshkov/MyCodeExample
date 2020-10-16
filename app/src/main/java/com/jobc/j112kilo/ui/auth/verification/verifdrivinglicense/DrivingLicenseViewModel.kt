package com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense.data.model.LicenseFormState
import com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense.domain.LicenseInteractor
import com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense.domain.LicenseInteractorOut
import com.jobc.j112kilo.utils.SingleLiveEvent
import javax.inject.Inject

class DrivingLicenseViewModel @Inject constructor(
    private val interactor: LicenseInteractor
) : ViewModel(), LicenseInteractorOut {

    init {
        interactor.setupInteractorOut(this)
    }

    private val loadingMut = MutableLiveData<Boolean>()
    private val errorMut: MutableLiveData<Unit> = SingleLiveEvent()
    private val loadFirstImgMut = MutableLiveData<Bitmap>()
    private val loadSecondImgMut = MutableLiveData<Bitmap>()
    private val licenseFormStateMut = MutableLiveData<LicenseFormState>()

    val loading: LiveData<Boolean> get() = loadingMut
    val error: LiveData<Unit> get() = errorMut
    val loadImgFirst: LiveData<Bitmap> get() = loadFirstImgMut
    val loadImgSecond: LiveData<Bitmap> get() = loadSecondImgMut
    val licenseFormState: LiveData<LicenseFormState> get() = licenseFormStateMut

    fun loadingImage(url: String) {
        interactor.loadingImage(url)
    }

    fun changeDataLicense(
        issuedBy: String,
        data: String,
        series: String,
        number: String,
        photoFirst: String,
        photoSecond: String
    ) {
        interactor.changeDataLicense(
            issuedBy,
            data,
            series,
            number,
            photoFirst,
            photoSecond
        )
    }

    override fun isLoading(loading: Boolean) {
        loadingMut.value = loading
    }

    override fun onLoadedFirstImg(bitmap: Bitmap) {
        loadFirstImgMut.value = bitmap
    }

    override fun onLoadedSecondImg(bitmap: Bitmap) {
        loadSecondImgMut.value = bitmap
    }

    override fun onError(e: Throwable) {
        errorMut.value = Unit
    }

    override fun onChangeLicenseFormState(licenseFormState: LicenseFormState) {
        licenseFormStateMut.value = licenseFormState
    }
}