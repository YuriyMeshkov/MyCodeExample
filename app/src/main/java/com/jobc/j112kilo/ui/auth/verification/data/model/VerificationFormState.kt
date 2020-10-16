package com.jobc.j112kilo.ui.auth.verification.data.model

data class VerificationFormState(
    val contactsDataError: Boolean = false,
    val personalDataError: Boolean = false,
    val drivingLicenseError: Boolean = false,
    val carsError: Boolean = false,
    val isDataValid: Boolean = false
)
