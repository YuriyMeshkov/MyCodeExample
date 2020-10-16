package com.jobc.j112kilo.api.dto.authdto

import com.google.gson.annotations.SerializedName
import com.jobc.j112kilo.api.dto.ResponseDto

data class RegistrationDto (
    @SerializedName("data") val data: RegistrationDataDto,
    @SerializedName("errors") val errors: List<String>
) : ResponseDto

data class RegistrationDataDto(
    @SerializedName("status") val success: Boolean,
)