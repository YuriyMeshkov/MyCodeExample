package com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.data.model

import com.jobc.j112kilo.data.model.Model

data class CarFormState(
    val carTypeError: String? = null,
    val carModelError: String? = null,
    val carRegistrationNumError: String? = null,
    val carPhotoChangeError: Boolean = false,
    val carPassportChangeError: Boolean = false,
    val isDataValid: Boolean = false
) : Model