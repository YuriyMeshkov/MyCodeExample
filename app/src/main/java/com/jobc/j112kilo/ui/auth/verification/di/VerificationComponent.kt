package com.jobc.j112kilo.ui.auth.verification.di

import com.jobc.j112kilo.di.AppComponent
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.ui.auth.verification.VerificationFragment
import dagger.Component

@ScreenScope
@Component(
    dependencies = [AppComponent::class],
    modules =  [VerificationModule::class, VerificationViewModelModule::class]
)
interface VerificationComponent {

    fun inject(verificationFragment: VerificationFragment)

    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: AppComponent) : Builder
        fun build() : VerificationComponent
    }

    companion object {
        fun init(appComponent: AppComponent) : VerificationComponent {
            return DaggerVerificationComponent
                .builder()
                .appComponent(appComponent)
                .build()
        }
    }
}