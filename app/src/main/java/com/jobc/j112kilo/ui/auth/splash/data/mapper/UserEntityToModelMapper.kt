package com.jobc.j112kilo.ui.auth.splash.data.mapper

import com.jobc.j112kilo.data.mapper.MapperFromDatabase
import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.storage.database.user.UserEntity

class UserEntityToModelMapper : MapperFromDatabase<UserEntity, UserModel>() {
    override fun map(from: UserEntity): UserModel {
        return UserModel(
            role = from.userRole,
            country = from.country,
            pathToAvatar = from.pathAvatar,
            userFirstName = from.userFirstName,
            userSurName = from.userSurName,
            userGender = from.userGender,
            cityOfResidence = from.cityOfResidence,
            documentApprovalAgreement = from.documentApprovalAgreement,
            documentApprovalPolicy = from.documentApprovalPolicy,
            userPhone = from.userPhone,
            acceptUserPhone = from.acceptUserPhone,
            userEmail = from.userEmail,
            acceptUserEmail = from.acceptUserEmail,
            verificationContacts = from.verificationContacts,
            verificationPersonalData = from.verificationPersonData,
            licenseIssuedBy = from.licenseIssuedBy,
            licenseDateOfIssue = from.licenseDataOfIssue,
            licenseSeries = from.licenseSeries,
            licenseNumber = from.licenseNumber,
            pathToPhotoFirstLicense = from.pathToPhotoFirstLicense,
            pathToPhotoSecondLicense = from.pathToPhotoSecondLicense,
            verificationLicense = from.verificationLicense,
            verificationCars = from.verificationCars
        )
    }
}