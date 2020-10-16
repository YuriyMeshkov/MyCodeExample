package com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.domain

import com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.data.model.CitySearchHistory
import com.jobc.j112kilo.domain.InteractorOut

interface CityChoiceInteractorOut : InteractorOut{
    fun isLoading(loading: Boolean)
    fun onError(e: Throwable)
    fun onLoaded(citySearchHistory: CitySearchHistory)
}