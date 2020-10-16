package com.jobc.j112kilo.data.model

data class UserTokenModel(
    var token: String? = null,
    var verified: Boolean = false,
    var userId: Int = 0,
    var role: String = "",
    var deviceId: String? = null
) : Model