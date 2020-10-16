package com.jobc.j112kilo.ui.auth.verification.verifpersonaldata.domain

import com.jobc.j112kilo.domain.InteractorImpl
import javax.inject.Inject

class PersonalInteractorImpl @Inject constructor(private val repo: PersonalRepo) :
    InteractorImpl<PersonalInteractorOut>(),
    PersonalInteractor {

    override fun loadingImage(pathImage: String) {
        LaunchSafely(
            {out.isLoading(it)},
            {out.onError(it)}
        ) {
            repo.loadingImage(pathImage)?.let { out.onLoaded(it) }
        }
    }
}