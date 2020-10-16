package com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model

data class RegistrationAccountModel (
    var userRole: String = "",
    var country: String = "",
    var userPhone: String = "",
    var userName: String = "",
    var userSurname: String = "",
    var userGender: String = "",
    var email: String ="",
    var cityOfResidence: String = "",
    var documentApprovalAgreement: Boolean = false,
    var documentApprovalPolicy: Boolean = false
)