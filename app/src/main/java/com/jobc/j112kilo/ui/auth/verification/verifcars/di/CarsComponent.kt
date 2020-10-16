package com.jobc.j112kilo.ui.auth.verification.verifcars.di

import com.jobc.j112kilo.di.AppComponent
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.ui.auth.verification.verifcars.CarsFragment
import dagger.Component

@ScreenScope
@Component(
    dependencies = [AppComponent::class]
)
interface CarsComponent {

    fun inject(carsFragment: CarsFragment)

    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: AppComponent) : Builder
        fun build() : CarsComponent
    }

    companion object {
        fun init(appComponent: AppComponent) : CarsComponent {
            return DaggerCarsComponent
                .builder()
                .appComponent(appComponent)
                .build()
        }
    }
}