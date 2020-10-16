package com.jobc.j112kilo.ui.auth.splash.domain

import com.jobc.j112kilo.data.model.CarsModel
import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.data.model.UserTokenModel
import com.jobc.j112kilo.domain.InteractorOut

interface SplashInteractorOut : InteractorOut {
    fun isLoading(loading: Boolean)
    fun onError(e: Throwable)
    fun receivedUserToken(userTokenModel: UserTokenModel?)
    fun receivedUserData(userModel: UserModel?)
    fun receivedCarsData(carsModel: CarsModel?)
}