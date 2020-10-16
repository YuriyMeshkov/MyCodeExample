package com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.domain

import com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.data.model.CitySearchHistory
import com.jobc.j112kilo.domain.Repository

interface CityChoiceRepo : Repository {
    suspend fun getCityFromGoogle(city: String) : List<CitySearchHistory>
    suspend fun saveToDatabaseChoiceCity(citySearchHistory: CitySearchHistory)
    suspend fun getCityFromDatabase() : List<CitySearchHistory>
}