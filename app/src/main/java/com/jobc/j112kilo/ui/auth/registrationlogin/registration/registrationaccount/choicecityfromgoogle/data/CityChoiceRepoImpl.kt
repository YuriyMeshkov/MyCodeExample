package com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.data

import com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.data.model.CitySearchHistory
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.domain.CityChoiceRepo
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.storage.CityFoundDao
import javax.inject.Inject

class CityChoiceRepoImpl @Inject constructor(private  val database: CityFoundDao) : CityChoiceRepo {

    override suspend fun getCityFromGoogle(city: String): List<CitySearchHistory> {
        TODO("Not yet implemented")
    }

    override suspend fun saveToDatabaseChoiceCity(citySearchHistory: CitySearchHistory) {
        TODO("Not yet implemented")
    }

    override suspend fun getCityFromDatabase(): List<CitySearchHistory> {
        TODO("Not yet implemented")
    }
}