package com.jobc.j112kilo.ui.imgtransformation

import android.content.ContentResolver
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jobc.j112kilo.ui.imgtransformation.domain.ImgTransformInteractor
import com.jobc.j112kilo.ui.imgtransformation.domain.ImgTransformInteractorOut
import com.jobc.j112kilo.utils.SingleLiveEvent
import javax.inject.Inject

class ImgTransformationViewModel @Inject constructor(val interactor: ImgTransformInteractor) :
    ViewModel(), ImgTransformInteractorOut {

    init {
        interactor.setupInteractorOut(this)
        //interactor.writeImage()
    }

    private val writingOrLoadingMut = MutableLiveData<Boolean>()
    private val errorMut: MutableLiveData<Unit> = SingleLiveEvent()
    private val loadImgMut = MutableLiveData<Bitmap>()

    val writingOrLoading: LiveData<Boolean> get() = writingOrLoadingMut
    val error: LiveData<Unit> get() = errorMut
    val loadImg: LiveData<Bitmap> get() = loadImgMut


    fun loadingImage(url: String) {
        interactor.loadingImage(url)
    }

    fun loadingImageFromGallery(pathImage: String, contentResolver: ContentResolver) {
        interactor.loadingImageFromGallery(pathImage, contentResolver)
    }

    fun writeImage(bitmap: Bitmap, fileName: String, filePath: String) {
        interactor.writeImage(bitmap, fileName, filePath)
    }


    override fun isWriteOrLoading(loading: Boolean) {
        writingOrLoadingMut.value = loading
    }

    override fun onError(e: Throwable) {
        errorMut.value = Unit
    }

    override fun onLoaded(bitmap: Bitmap) {
        loadImgMut.value = bitmap
    }




}