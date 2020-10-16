package com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense.di

import com.jobc.j112kilo.di.AppComponent
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense.DrivingLicenseFragment
import dagger.Component

@ScreenScope
@Component(
    dependencies = [AppComponent::class],
    modules = [LicenseModule::class, LicenseViewModelModule::class]
)
interface LicenseComponent {

    fun inject(drivingLicenseFragment: DrivingLicenseFragment)

    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: AppComponent) : Builder
        fun build() : LicenseComponent
    }

    companion object {
        fun init(appComponent: AppComponent) : LicenseComponent {
            return DaggerLicenseComponent
                .builder()
                .appComponent(appComponent)
                .build()
        }
    }
}