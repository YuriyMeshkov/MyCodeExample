package com.jobc.j112kilo.ui.auth.registrationlogin.login.data.model

import com.jobc.j112kilo.api.dto.authdto.ErrorsDto
import com.jobc.j112kilo.api.dto.authdto.UserTokenResponseDto
import com.jobc.j112kilo.data.mapper.MapperFromResponse
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.ErrorModel
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.UserTokenResponseModel

class LoginMapperFromResponse : MapperFromResponse<UserTokenResponseDto, UserTokenResponseModel>() {
    override fun map(from: UserTokenResponseDto): UserTokenResponseModel {
        return UserTokenResponseModel(
            token = from.token,
            //errors = getErrors(from.errors[0]),
            verified = from.verified,
            userId = from.userId,
            role = from.role,
            deviceId = from.deviceId
        )
    }

    private fun getErrors(errors: ErrorsDto) =
        ErrorModel(
            attribute = errors.attribute,
            label = errors.label,
            error = errors.error
        )
}