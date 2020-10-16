package com.jobc.j112kilo.ui.auth.data.maper

import com.jobc.j112kilo.api.dto.authdto.RegistrationSendSmsDto
import com.jobc.j112kilo.data.mapper.MapperFromResponse
import com.jobc.j112kilo.ui.auth.data.model.ResponseToSendSmsModel

class ResponseToSendSmsMapper : MapperFromResponse<RegistrationSendSmsDto, ResponseToSendSmsModel>() {
    override fun map(from: RegistrationSendSmsDto): ResponseToSendSmsModel {
        return ResponseToSendSmsModel(
            from.data.status,
            from.data.phone,
            from.data.codeSms,
            from.data.info,
            /*errors = when (from.errorsList.isNotEmpty()) {
                true -> from.errorsList[0].errorsEmail?.error?.get(0)
                false -> null
            }*/
        )
    }


}