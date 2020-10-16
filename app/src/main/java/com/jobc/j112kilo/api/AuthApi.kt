package com.jobc.j112kilo.api
import com.jobc.j112kilo.api.dto.authdto.*
import retrofit2.http.*

interface AuthApi {

    @POST(value = "auth?")
    suspend fun getTokenFromEmail(@Query(value = "login") login: String,
                                  @Query(value = "password") password: String) : UserTokenResponseDto

    @POST(value = "auth?")
    suspend fun getTokenFromPhone(@Query(value = "phone") login: String,
                                  @Query(value = "password") password: String) : UserTokenResponseDto

    @POST(value = "registration/register")
    suspend fun registrationUser(@Body parameter: HashMap<String, String>) : RegistrationDto

    @POST(value = "registration/send-sms")
    suspend fun sendSmsCode(@Body parameter: HashMap<String, String>) : RegistrationSendSmsDto

    @POST(value = "user/account/confirm")
    suspend fun registrationAccount(
        @Header(value = "Authorization") token: String,
        @Body parameter: HashMap<String, String>
    ) : RegistrationAccountDto

    @POST(value = "user/account/change-phone")
    suspend fun sendSmsForChangePhone(
        @Header(value = "Authorization") token: String,
        @Body parameter: HashMap<String, String>
    ) : RegistrationSendSmsDto

    @POST(value = "user/account/change-email")
    suspend fun sendSmsForChangeEmail(
        @Header(value = "Authorization") token: String,
        @Body parameter: HashMap<String, String>
    ) : RegistrationSendSmsDto

    @POST(value = "user/account/confirm-driver")
    suspend fun sendDataVerification(
        @Header(value = "Authorization") token: String,
        @Body parameter: HashMap<String, String>
    ) : VerificationDto
}