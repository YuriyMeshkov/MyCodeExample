package com.jobc.j112kilo.ui.auth.splash.domain

import com.jobc.j112kilo.domain.Interactor

interface SplashInteractor : Interactor<SplashInteractorOut> {
    fun getUserTokenFromDatabase()
    fun getUserDataFromDatabase()
    fun getCarsData()
}