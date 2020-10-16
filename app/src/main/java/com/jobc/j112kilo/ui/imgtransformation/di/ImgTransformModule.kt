package com.jobc.j112kilo.ui.imgtransformation.di


import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.storage.ImageDataSource
import com.jobc.j112kilo.ui.imgtransformation.data.ImgTransformRepoImpl
import com.jobc.j112kilo.ui.imgtransformation.domain.ImgTransformInteractor
import com.jobc.j112kilo.ui.imgtransformation.domain.ImgTransformInteractorImpl
import com.jobc.j112kilo.ui.imgtransformation.domain.ImgTransformRepo
import dagger.Module
import dagger.Provides

@Module
class ImgTransformModule {

    @ScreenScope
    @Provides
    fun providerImgTransform(imageDataSource: ImageDataSource) : ImgTransformRepo =
        ImgTransformRepoImpl(imageDataSource)

    @ScreenScope
    @Provides
    fun providerImgTransformInteractor(repo: ImgTransformRepo) : ImgTransformInteractor =
        ImgTransformInteractorImpl(repo)

}