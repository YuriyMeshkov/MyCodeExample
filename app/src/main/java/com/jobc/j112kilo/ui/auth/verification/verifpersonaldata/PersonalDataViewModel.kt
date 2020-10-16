package com.jobc.j112kilo.ui.auth.verification.verifpersonaldata

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jobc.j112kilo.ui.auth.verification.verifpersonaldata.domain.PersonalInteractor
import com.jobc.j112kilo.ui.auth.verification.verifpersonaldata.domain.PersonalInteractorOut
import com.jobc.j112kilo.utils.SingleLiveEvent
import javax.inject.Inject

class PersonalDataViewModel  @Inject constructor(
    private val interactor: PersonalInteractor
) : ViewModel(), PersonalInteractorOut {

    init {
        interactor.setupInteractorOut(this)
    }

    private val loadingMut = MutableLiveData<Boolean>()
    private val errorMut: MutableLiveData<Unit> = SingleLiveEvent()
    private val loadImgMut = MutableLiveData<Bitmap>()

    val loading: LiveData<Boolean> get() = loadingMut
    val error: LiveData<Unit> get() = errorMut
    val loadImg: LiveData<Bitmap> get() = loadImgMut


    fun loadingImage(url: String) {
        interactor.loadingImage(url)
    }

    override fun isLoading(loading: Boolean) {
        loadingMut.value = loading
    }

    override fun onLoaded(bitmap: Bitmap) {
        loadImgMut.value = bitmap
    }

    override fun onError(e: Throwable) {
        errorMut.value = Unit
    }

}