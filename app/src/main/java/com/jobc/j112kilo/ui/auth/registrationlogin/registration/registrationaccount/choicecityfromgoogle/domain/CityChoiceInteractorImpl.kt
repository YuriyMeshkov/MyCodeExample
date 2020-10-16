package com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.domain

import com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.data.model.CitySearchHistory
import com.jobc.j112kilo.domain.InteractorImpl
import javax.inject.Inject

class CityChoiceInteractorImpl @Inject constructor(
    private val choiceRepo: CityChoiceRepo
) : InteractorImpl<CityChoiceInteractorOut>(), CityChoiceInteractor {

    override fun getCitiesFromGoogle(city: String): List<CitySearchHistory> {
        TODO("Not yet implemented")
    }

    override fun getCitiesFromDatabase(): List<CitySearchHistory> {
        TODO("Not yet implemented")
    }

    override fun saveCityToDatabase(citySearchHistory: CitySearchHistory) {
        TODO("Not yet implemented")
    }

}