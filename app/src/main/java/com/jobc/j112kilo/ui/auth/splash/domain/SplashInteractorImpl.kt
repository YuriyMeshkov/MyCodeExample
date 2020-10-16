package com.jobc.j112kilo.ui.auth.splash.domain

import com.jobc.j112kilo.domain.InteractorImpl
import javax.inject.Inject

class SplashInteractorImpl @Inject constructor(
    private val repo: SplashRepo
) : InteractorImpl<SplashInteractorOut>(), SplashInteractor {

    override fun getUserTokenFromDatabase() {
        LaunchSafely(
            { out.isLoading(it) },
            { out.onError(it) }
        ) {
            out.receivedUserToken(repo.getUserToken())
        }
    }

    override fun getUserDataFromDatabase() {
        LaunchSafely(
            { out.isLoading(it) },
            { out.onError(it) }
        ) {
            out.receivedUserData(repo.getUserData())
        }
    }

    override fun getCarsData() {
        LaunchSafely(
            { out.isLoading(it) },
            { out.onError(it) }
        ) {
            out.receivedCarsData(repo.getCarsData())
        }
    }
}