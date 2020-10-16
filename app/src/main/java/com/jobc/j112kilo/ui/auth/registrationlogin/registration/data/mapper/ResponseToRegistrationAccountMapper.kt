package com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.mapper

import com.jobc.j112kilo.api.dto.authdto.RegistrationAccountDto
import com.jobc.j112kilo.data.mapper.MapperFromResponse
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.ResponseToRegistrationAccountModel

class ResponseToRegistrationAccountMapper :
    MapperFromResponse<RegistrationAccountDto, ResponseToRegistrationAccountModel>() {

    override fun map(from: RegistrationAccountDto): ResponseToRegistrationAccountModel {
        return ResponseToRegistrationAccountModel(
            from.data.status,
            from.data.info,
            errors = when (from.errors.isNotEmpty()) {
                true -> from.errors[0]
                false -> null
            }
        )
    }
}