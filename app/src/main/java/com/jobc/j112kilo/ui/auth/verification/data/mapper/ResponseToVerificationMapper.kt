package com.jobc.j112kilo.ui.auth.verification.data.mapper

import com.jobc.j112kilo.api.dto.authdto.VerificationDto
import com.jobc.j112kilo.data.mapper.MapperFromResponse
import com.jobc.j112kilo.ui.auth.verification.data.model.VerificationModel

class ResponseToVerificationMapper : MapperFromResponse<VerificationDto, VerificationModel>() {

    override fun map(from: VerificationDto): VerificationModel {
        return VerificationModel(
            from.data.success,
            from.data.info,
            from.errors[0]
        )
    }
}