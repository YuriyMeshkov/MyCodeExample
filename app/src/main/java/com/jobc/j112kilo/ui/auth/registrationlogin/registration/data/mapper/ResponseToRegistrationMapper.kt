package com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.mapper

import com.jobc.j112kilo.api.dto.authdto.RegistrationDto
import com.jobc.j112kilo.data.mapper.MapperFromResponse
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.ResponseToRegistrationModel

class ResponseToRegistrationMapper : MapperFromResponse<RegistrationDto, ResponseToRegistrationModel>() {
    override fun map(from: RegistrationDto): ResponseToRegistrationModel {
        return ResponseToRegistrationModel(
            from.data.success,
            when (from.errors.isNotEmpty()) {
                true -> from.errors[0]
                false -> null
            }
        )
    }
}