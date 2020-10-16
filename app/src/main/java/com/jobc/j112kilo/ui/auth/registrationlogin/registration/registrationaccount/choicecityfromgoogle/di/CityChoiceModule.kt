package com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.di

import android.content.Context
import androidx.room.Room
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.data.CityChoiceRepoImpl
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.domain.CityChoiceInteractor
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.domain.CityChoiceInteractorImpl
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.domain.CityChoiceRepo
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.storage.CityFoundDao
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.storage.CityFoundDatabase
import com.jobc.j112kilo.utils.CITY_FOUND_DATABASE
import dagger.Module
import dagger.Provides

@Module
class CityChoiceModule {

    @ScreenScope
    @Provides
    fun provideCityFoundDatabase(context: Context) : CityFoundDatabase =
        Room.databaseBuilder(context, CityFoundDatabase::class.java, CITY_FOUND_DATABASE).build()

    @ScreenScope
    @Provides
    fun provideCityChoiceInteractor(repo: CityChoiceRepo) : CityChoiceInteractor =
        CityChoiceInteractorImpl(repo)

    @ScreenScope
    @Provides
    fun provideCityChoiceRepo(database: CityFoundDao) : CityChoiceRepo =
        CityChoiceRepoImpl(database)

}