package com.jobc.j112kilo.ui.auth.registrationlogin.login.di

import com.jobc.j112kilo.di.AppComponent
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.ui.auth.registrationlogin.login.LoginFragment
import dagger.Component

@ScreenScope
@Component(
    dependencies = [AppComponent::class],
    modules = [LoginViewModelModule::class, LoginModule::class]
)
interface LoginComponent {

    fun inject(loginFragment: LoginFragment)

    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: AppComponent): Builder
        fun build(): LoginComponent
    }

    companion object {
        fun init(appComponent: AppComponent) : LoginComponent {
            return DaggerLoginComponent
                .builder()
                .appComponent(appComponent)
                .build()
        }
    }
}