package com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.di

import com.jobc.j112kilo.di.AppComponent
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.AddCarFragment
import dagger.Component

@ScreenScope
@Component(
    dependencies = [AppComponent::class],
    modules = [AddCarViewModelModule::class, AddCarsModule::class]
)
interface AddCarComponent {

    fun inject(addCarFragment: AddCarFragment)

    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: AppComponent) : Builder
        fun build() : AddCarComponent
    }

    companion object {
        fun init(appComponent: AppComponent) : AddCarComponent {
            return DaggerAddCarComponent
                .builder()
                .appComponent(appComponent)
                .build()
        }
    }
}
