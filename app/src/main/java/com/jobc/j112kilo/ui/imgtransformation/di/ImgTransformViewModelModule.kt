package com.jobc.j112kilo.ui.imgtransformation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.di.ViewModelFactory
import com.jobc.j112kilo.di.ViewModelKey
import com.jobc.j112kilo.di.ViewModelModule
import com.jobc.j112kilo.ui.imgtransformation.ImgTransformationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ImgTransformViewModelModule : ViewModelModule {

    @ScreenScope
    @Binds
    @IntoMap
    @ViewModelKey(ImgTransformationViewModel::class)
    fun bindsImgTransformViewModelModule(viewModel: ImgTransformationViewModel) : ViewModel
}