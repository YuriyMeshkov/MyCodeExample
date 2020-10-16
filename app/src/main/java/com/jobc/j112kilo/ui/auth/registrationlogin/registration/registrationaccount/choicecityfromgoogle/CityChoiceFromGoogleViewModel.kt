package com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle

import androidx.lifecycle.ViewModel
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.data.model.CitySearchHistory
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.domain.CityChoiceInteractor
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.domain.CityChoiceInteractorOut

class CityChoiceFromGoogleViewModel constructor(
    private val interactor: CityChoiceInteractor
) : ViewModel(), CityChoiceInteractorOut {

    override fun isLoading(loading: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onError(e: Throwable) {
        TODO("Not yet implemented")
    }

    override fun onLoaded(citySearchHistory: CitySearchHistory) {
        TODO("Not yet implemented")
    }


}