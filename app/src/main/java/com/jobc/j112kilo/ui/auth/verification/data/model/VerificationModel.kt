package com.jobc.j112kilo.ui.auth.verification.data.model

import com.jobc.j112kilo.data.model.Model

data class VerificationModel (
    val status: Boolean = false,
    val info: String? = null,
    val error: String? = null
) : Model