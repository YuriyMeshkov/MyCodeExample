package com.jobc.j112kilo.api.dto.authdto

import com.google.gson.annotations.SerializedName
import com.jobc.j112kilo.api.dto.ResponseDto

data class RegistrationSendSmsDto(
    @SerializedName("data") val  data: RegistrationSendSmsDataDto,
    //@SerializedName("errors") val errorsList: List<RegistrationSendSmsErrorsDto>,
    //@SerializedName("errors") val errors: RegistrationSendSmsErrorsDto

) : ResponseDto

data class RegistrationSendSmsDataDto(
    @SerializedName("status") val status: Boolean,
    @SerializedName("phone") val phone: String,
    @SerializedName("code") val codeSms: String,
    @SerializedName("info") val info: String
)

data class RegistrationSendSmsErrorsDto(
    @SerializedName("email") val errorsEmail: RegistrationSendSmsErrorsEmailDto? = null
)

data class RegistrationSendSmsErrorsEmailDto(
    @SerializedName("error") val error: List<String>? = null
)
