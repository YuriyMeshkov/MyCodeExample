package com.jobc.j112kilo.api.dto.authdto

import com.google.gson.annotations.SerializedName
import com.jobc.j112kilo.api.dto.ResponseDto

data class RegistrationAccountDto(
    @SerializedName("data") val data: RegistrationAccountDataDto,
    @SerializedName("errors") val errors: List<String>
) : ResponseDto

data class RegistrationAccountDataDto(
    @SerializedName("status") val status: Boolean,
    @SerializedName("info") val info: String
)