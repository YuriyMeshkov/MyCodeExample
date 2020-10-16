package com.jobc.j112kilo.ui.auth.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jobc.j112kilo.data.model.CarsModel
import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.data.model.UserTokenModel
import com.jobc.j112kilo.ui.auth.splash.domain.SplashInteractor
import com.jobc.j112kilo.ui.auth.splash.domain.SplashInteractorOut
import com.jobc.j112kilo.utils.ERROR_DATA
import com.jobc.j112kilo.utils.SingleLiveEvent
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val interactor: SplashInteractor
): ViewModel(), SplashInteractorOut {

    init {
        interactor.setupInteractorOut(this)
    }

    private val loadingMut = MutableLiveData<Boolean>()
    private val errorMut: MutableLiveData<Unit> = SingleLiveEvent()
    private val userTokenMut: MutableLiveData<UserTokenModel> = SingleLiveEvent()
    private val userMut: MutableLiveData<UserModel> = SingleLiveEvent()
    private val carsMut: MutableLiveData<CarsModel> = SingleLiveEvent()

    val loading: LiveData<Boolean> get() = loadingMut
    val error: LiveData<Unit> get() = errorMut
    val userToken: LiveData<UserTokenModel> get() = userTokenMut
    val user: LiveData<UserModel> get() = userMut
    val cars: LiveData<CarsModel> get() = carsMut

    fun getUserTokenFromDatabase() {
        interactor.getUserTokenFromDatabase()
    }

    fun getUserDataFromDatabase() {
        interactor.getUserDataFromDatabase()
    }

    fun getCarsData() {
        interactor.getCarsData()
    }

    override fun isLoading(loading: Boolean) {
        loadingMut.value = loading
    }

    override fun onError(e: Throwable) {
        errorMut.value = Unit
    }

    override fun receivedUserToken(userTokenModel: UserTokenModel?) {
        when(userTokenModel != null) {
            true -> userTokenMut.value = userTokenModel
            false -> userTokenMut.value = UserTokenModel()
        }
    }

    override fun receivedUserData(userModel: UserModel?) {
        when(userModel != null) {
            true -> userMut.value = userModel
            false -> userMut.value = UserModel()
        }
    }

    override fun receivedCarsData(carsModel: CarsModel?) {
        when(carsModel != null) {
            true -> carsMut.value = carsModel
            false -> carsMut.value = CarsModel()
        }
    }
}