package com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.di

import com.jobc.j112kilo.di.AppComponent
import com.jobc.j112kilo.di.ScreenScope
import dagger.Component

@ScreenScope
@Component(
    dependencies = [AppComponent::class],
    modules = [CityChoiceModule::class]
)
interface CityChoiceComponent {

    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: AppComponent) : Builder
        fun build() : CityChoiceComponent
    }

    companion object {
        fun init(appComponent: AppComponent) : CityChoiceComponent {
            return DaggerCityChoiceComponent
                .builder()
                .appComponent(appComponent)
                .build()
        }
    }
}