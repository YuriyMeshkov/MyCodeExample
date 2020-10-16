package com.jobc.j112kilo.ui.auth.splash.di

import com.jobc.j112kilo.di.AppComponent
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.ui.auth.splash.SplashActivity
import dagger.Component

@ScreenScope
@Component(
    dependencies = [AppComponent::class],
    modules = [SplashModule::class, SplashViewModelModule::class]
)
interface SplashComponent {

    fun inject(splashActivity: SplashActivity)

    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: AppComponent) : Builder
        fun build() : SplashComponent
    }

    companion object {
        fun init(appComponent: AppComponent) : SplashComponent {
            return DaggerSplashComponent
                .builder()
                .appComponent(appComponent)
                .build()
        }
    }
}