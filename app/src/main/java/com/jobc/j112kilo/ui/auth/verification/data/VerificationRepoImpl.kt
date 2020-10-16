package com.jobc.j112kilo.ui.auth.verification.data

import com.jobc.j112kilo.api.AuthApi
import com.jobc.j112kilo.data.RepositoryImpl
import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.data.model.UserTokenModel
import com.jobc.j112kilo.storage.database.user.UserDao
import com.jobc.j112kilo.storage.database.user.UserEntity
import com.jobc.j112kilo.storage.database.usertoken.UserTokenDao
import com.jobc.j112kilo.storage.database.usertoken.UserTokenEntity
import com.jobc.j112kilo.ui.auth.verification.data.mapper.ResponseToVerificationMapper
import com.jobc.j112kilo.ui.auth.verification.domain.VerificationRepo
import javax.inject.Inject

class VerificationRepoImpl @Inject constructor(
    private val authApi: AuthApi,
    private val dataBaseUserToken: UserTokenDao,
    private val dataBaseUser: UserDao
) : RepositoryImpl(), VerificationRepo {

    override suspend fun sendingUserDataToServer(
        user: UserModel,
        userToken: UserTokenModel,
        token: String,
        parameters: HashMap<String, String>
    ) : Unit = ioAsync {
        /*val result = authApi.sendDataVerification(
            token,
            parameters
        )
        ResponseToVerificationMapper().map(result).apply {
            with(user) {
                dataBaseUser.insert(
                    UserEntity(
                        userRole = role,
                        country = country,
                        pathAvatar = pathToAvatar,
                        userFirstName = userFirstName,
                        userSurName = userSurName,
                        userGender = userGender,
                        cityOfResidence = cityOfResidence,
                        documentApprovalAgreement = documentApprovalAgreement,
                        documentApprovalPolicy = documentApprovalPolicy,
                        userPhone = userPhone,
                        acceptUserPhone = acceptUserPhone,
                        userEmail = userEmail,
                        acceptUserEmail = acceptUserEmail,
                        verificationContacts = verificationContacts,
                        verificationPersonData = verificationPersonalData,
                        licenseIssuedBy = licenseIssuedBy,
                        licenseDataOfIssue = licenseDateOfIssue,
                        licenseSeries = licenseSeries,
                        licenseNumber = licenseNumber,
                        pathToPhotoFirstLicense = pathToPhotoFirstLicense,
                        pathToPhotoSecondLicense = pathToPhotoSecondLicense,
                        verificationLicense = verificationLicense,
                        verificationCars = verificationCars
                    )
                )
            }
            saveToDatabaseUserToken(userToken)
        }*/

        with(user) {
            dataBaseUser.insert(
                UserEntity(
                    userRole = role,
                    country = country,
                    pathAvatar = pathToAvatar,
                    userFirstName = userFirstName,
                    userSurName = userSurName,
                    userGender = userGender,
                    cityOfResidence = cityOfResidence,
                    documentApprovalAgreement = documentApprovalAgreement,
                    documentApprovalPolicy = documentApprovalPolicy,
                    userPhone = userPhone,
                    acceptUserPhone = acceptUserPhone,
                    userEmail = userEmail,
                    acceptUserEmail = acceptUserEmail,
                    verificationContacts = verificationContacts,
                    verificationPersonData = verificationPersonalData,
                    licenseIssuedBy = licenseIssuedBy,
                    licenseDataOfIssue = licenseDateOfIssue,
                    licenseSeries = licenseSeries,
                    licenseNumber = licenseNumber,
                    pathToPhotoFirstLicense = pathToPhotoFirstLicense,
                    pathToPhotoSecondLicense = pathToPhotoSecondLicense,
                    verificationLicense = verificationLicense,
                    verificationCars = verificationCars
                )
            )
        }
        saveToDatabaseUserToken(userToken)
    }

    override suspend fun saveToDatabaseUserToken(userToken: UserTokenModel) : Unit = ioAsync {
        dataBaseUserToken.insert(
            UserTokenEntity(
                token = userToken.token!!,
                verified = userToken.verified,
                userId = userToken.userId,
                role = userToken.role,
                deviceId = userToken.deviceId
            )
        )
    }
}