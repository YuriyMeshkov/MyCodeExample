package com.jobc.j112kilo.api.dto.authdto

import com.google.gson.annotations.SerializedName
import com.jobc.j112kilo.api.dto.ResponseDto

data class VerificationDto (
    @SerializedName("data") val data: VerificationDataDto,
    @SerializedName("errors") val errors: List<String>
) : ResponseDto

data class VerificationDataDto(
    @SerializedName("status") val success: Boolean,
    @SerializedName("info") val info: String
)