package com.jobc.j112kilo.api.dto.authdto

import com.google.gson.annotations.SerializedName
import com.jobc.j112kilo.api.dto.ResponseDto

data class UserTokenResponseDto(
    @SerializedName("token") val token: String,
    @SerializedName("errors") val errors: List<ErrorsDto>, //MutableList<ErrorsDto>,
    @SerializedName("verified") val verified: Boolean,
    @SerializedName("userId") val userId: Int,
    @SerializedName("role") val role: String,
    @SerializedName("deviceId") val deviceId: String?
) : ResponseDto

data class ErrorsDto(
    @SerializedName("attribute") val attribute: String,
    @SerializedName("label") val label: String,
    @SerializedName("error") val error: List<String>,
    @SerializedName("verified") val verified: Boolean
) : ResponseDto
