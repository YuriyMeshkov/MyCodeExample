package com.jobc.j112kilo.ui.auth.verification.verifpersonaldata.di

import com.jobc.j112kilo.di.AppComponent
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.ui.auth.verification.verifpersonaldata.PersonalDataFragment
import dagger.Component

@ScreenScope
@Component(
    dependencies = [AppComponent::class],
    modules = [PersonalModule::class, PersonalViewModelModule::class]
)
interface PersonalComponent {

    fun inject(personalDataFragment: PersonalDataFragment)

    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: AppComponent) : Builder
        fun build() : PersonalComponent
    }

    companion object {
        fun init(appComponent: AppComponent) : PersonalComponent {
            return DaggerPersonalComponent
                .builder()
                .appComponent(appComponent)
                .build()
        }
    }
}