package com.jobc.j112kilo.data.model

import android.graphics.Bitmap

data class UserModel(
    var role: String = "",
    var country: String = "",
    var pathToAvatar: String = "",
    var avatar: Bitmap? = null,
    var userFirstName: String = "",
    var userSurName: String = "",
    var userGender: String = "",
    var cityOfResidence: String = "",
    var documentApprovalAgreement: Boolean = false,
    var documentApprovalPolicy: Boolean = false,
    var userPhone: String = "",
    var acceptUserPhone: Boolean = false,
    var userEmail: String = "",
    var acceptUserEmail: Boolean = false,
    var verificationContacts: Boolean = false,
    var verificationPersonalData: Boolean = false,
    var licenseIssuedBy: String = "",
    var licenseDateOfIssue: String = "",
    var licenseSeries: String = "",
    var licenseNumber: String = "",
    var pathToPhotoFirstLicense: String = "",
    var photoLicenseFirst: Bitmap? = null,
    var pathToPhotoSecondLicense: String = "",
    var photoLicenseSecond: Bitmap? = null,
    var verificationLicense: Boolean = false,
    var verificationCars: Boolean = false
) : Model