package com.jobc.j112kilo.ui.auth.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.jobc.j112kilo.App
import com.jobc.j112kilo.R
import com.jobc.j112kilo.data.model.Car
import com.jobc.j112kilo.data.model.CarsModel
import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.data.model.UserTokenModel
import com.jobc.j112kilo.ui.auth.splash.di.SplashComponent
import com.jobc.j112kilo.ui.auth.verification.VerificationActivity
import com.jobc.j112kilo.ui.main.MainActivity
import com.jobc.j112kilo.ui.preview.PreviewActivity
import com.jobc.j112kilo.utils.observe
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var user: UserModel
    @Inject
    lateinit var userToken: UserTokenModel
    @Inject
    lateinit var cars: CarsModel
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: SplashViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)
        SplashComponent.init((this.application as App).appComponent)
            .inject(this)
        initLiveData()
        getUserToken()
    }

    private fun initLiveData() {
        initUserTokenLiveData()
        initUserLiveData()
        initCarsLiveData()
    }

    private fun initUserTokenLiveData() {
        observe(viewModel.userToken) {
            when(it.token != null) {
                true -> {
                    updateUserToken(it)
                    /*if (it.verified) {
                        startMainActivity()
                    } else {
                        viewModel.getUserDataFromDatabase()
                    }*/
                    viewModel.getUserDataFromDatabase()
                }
                false -> startPreviewActivity()
            }
        }
    }

    private fun initUserLiveData() {
        observe(viewModel.user) {
            updateUser(it)

            when(it.verificationCars) {
                true -> viewModel.getCarsData()
                false -> startVerificationActivity()
            }




        }
    }

    private fun initCarsLiveData() {
        observe(viewModel.cars) {
            updateCars(it)

            /**
             * 88888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
             * Слкдующий блок надо убать, когда будут записаны верификации на сервер
             *
             */
            if(user.verificationCars && user.verificationLicense && user.verificationPersonalData && user.verificationContacts) {
                startMainActivity()
            } else {
                startVerificationActivity()
            }
        }
    }

    private fun getUserToken() {
        viewModel.getUserTokenFromDatabase()
    }

    private fun startPreviewActivity() {
        val intent = Intent(this, PreviewActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun startVerificationActivity() {
        val intent = Intent(this, VerificationActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun updateUser(source: UserModel) {
        with(user) {
            role = source.role
            country = source.country
            pathToAvatar = source.pathToAvatar
            avatar = source.avatar
            userFirstName = source.userFirstName
            userSurName = source.userSurName
            userGender = source.userGender
            cityOfResidence = source.cityOfResidence
            documentApprovalAgreement = source.documentApprovalAgreement
            documentApprovalPolicy = source.documentApprovalPolicy
            userPhone = source.userPhone
            acceptUserPhone = source.acceptUserPhone
            userEmail = source.userEmail
            acceptUserEmail = source.acceptUserEmail
            verificationContacts = source.verificationContacts
            verificationPersonalData = source.verificationPersonalData
            licenseIssuedBy = source.licenseIssuedBy
            licenseDateOfIssue = source.licenseDateOfIssue
            licenseSeries = source.licenseSeries
            licenseNumber = source.licenseNumber
            pathToPhotoSecondLicense = source.pathToPhotoSecondLicense
            photoLicenseFirst = source.photoLicenseFirst
            pathToPhotoFirstLicense = source.pathToPhotoFirstLicense
            photoLicenseSecond = source.photoLicenseSecond
            verificationLicense = source.verificationLicense
            verificationCars = source.verificationCars
        }
    }

    private fun updateUserToken(source: UserTokenModel) {
        with(userToken) {
            token = source.token
            verified = source.verified
            userId = source.userId
            role = source.role
            deviceId = source.deviceId
        }
    }

    private fun updateCars(source: CarsModel) {
        source.listCar.forEach {
            cars.listCar.add(
                Car(
                    carId = it.carId,
                    carType = it.carType,
                    carModel = it.carModel,
                    carRegistrationNum = it.carRegistrationNum,
                    pathToCarPhoto = it.pathToCarPhoto,
                    carPhotoChange = it.carPhotoChange,
                    pathToPassportPhotoFirst = it.pathToPassportPhotoFirst,
                    pathToPassportPhotoSecond = it.pathToPassportPhotoSecond,
                    carVerification = it.carVerification,
                    carPhoto = it.carPhoto
                )
            )
        }
    }
}