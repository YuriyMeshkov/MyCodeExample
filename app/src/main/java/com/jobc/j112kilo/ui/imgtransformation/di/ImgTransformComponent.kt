package com.jobc.j112kilo.ui.imgtransformation.di

import com.jobc.j112kilo.di.AppComponent
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.ui.imgtransformation.ImgTransformationFragment
import dagger.Component

@ScreenScope
@Component(
    dependencies = [AppComponent::class],
    modules = [ImgTransformModule::class, ImgTransformViewModelModule::class]
)
interface ImgTransformComponent {

    fun inject(imgImgTransformationFragment: ImgTransformationFragment)

    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: AppComponent): Builder

        fun build(): ImgTransformComponent
    }

    companion object {
        fun init(appComponent: AppComponent) : ImgTransformComponent {
            return DaggerImgTransformComponent
                .builder()
                .appComponent(appComponent)
                .build()
        }
    }

}