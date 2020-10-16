package com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense.data.model

import com.jobc.j112kilo.data.model.Model

data class LicenseFormState(
    val issuedByError: String? = null,
    val dataError: String? = null,
    val seriesError: String? = null,
    val numberError: String? = null,
    val photoFirstError: String? = null,
    val photoSecondError: String? = null,
    val isDataValid: Boolean = false
) : Model