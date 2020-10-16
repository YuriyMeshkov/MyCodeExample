package com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.domain

import com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.data.model.CitySearchHistory
import com.jobc.j112kilo.domain.Interactor

interface CityChoiceInteractor : Interactor<CityChoiceInteractorOut> {
    fun getCitiesFromGoogle(city: String) : List<CitySearchHistory>
    fun getCitiesFromDatabase() : List<CitySearchHistory>
    fun saveCityToDatabase(citySearchHistory: CitySearchHistory)
}