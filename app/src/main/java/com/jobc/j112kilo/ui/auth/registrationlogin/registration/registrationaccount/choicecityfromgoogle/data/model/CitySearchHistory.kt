package com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.data.model

import com.jobc.j112kilo.data.model.Model

data class CitySearchHistory(
    val cityName: String,
    val cityRegion: String,
    val cityLat: Double,
    val cityLng: Double,
    val history: Boolean
) : Model