package com.jobc.j112kilo.ui.auth.data.model

import com.jobc.j112kilo.data.model.Model

data class ResponseToSendSmsModel(
    val status: Boolean = false,
    val phone: String? = null,
    val codeSms: String? = null,
    val info: String? = null,
    val errors: String? = null
) : Model