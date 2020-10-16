package com.jobc.j112kilo.ui.auth.registrationlogin.registration.di

import com.jobc.j112kilo.di.AppComponent
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationwithphone.RegistrationFragment
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.RegistrationStep2Fragment
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.RegistrationAccountFragment
import dagger.Component

@ScreenScope
@Component(
    dependencies = [AppComponent::class],
    modules = [RegistrationModule::class, RegistrationViewModelModule::class]
)
interface RegistrationComponent {
    fun inject(registrationFragment: RegistrationFragment)
    fun inject(registrationStep2Fragment: RegistrationStep2Fragment)
    fun inject(registrationAccountFragment: RegistrationAccountFragment)

    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: AppComponent) : Builder
        fun build() : RegistrationComponent
    }

    companion object {
        fun init(appComponent: AppComponent) : RegistrationComponent {
            return DaggerRegistrationComponent
                .builder()
                .appComponent(appComponent)
                .build()
        }
    }
}