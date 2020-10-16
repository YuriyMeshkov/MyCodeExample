package com.jobc.j112kilo.ui.main.di

import com.jobc.j112kilo.di.AppComponent
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.ui.main.MainActivity
import dagger.Component

@ScreenScope
@Component(dependencies = [AppComponent::class],
    modules = []
)
interface MainComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: AppComponent): Builder
        fun build(): MainComponent
    }

    companion object {
        fun init(appComponent: AppComponent): MainComponent {
            return DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .build()
        }
    }
}