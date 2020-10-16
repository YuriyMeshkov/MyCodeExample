package com.jobc.j112kilo

import android.app.Application
import com.jobc.j112kilo.di.AppComponent
import com.jobc.j112kilo.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }
}