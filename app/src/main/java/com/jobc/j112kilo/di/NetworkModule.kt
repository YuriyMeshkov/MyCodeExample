package com.jobc.j112kilo.di

import com.jobc.j112kilo.BuildConfig
import com.jobc.j112kilo.api.AuthApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val BASE_URL = "https://*******/api/"
const val OKHTTP_CONNECT_TIMEOUT = 30L
const val OKHTTP_WRITE_TIMEOUT = 60L
const val OKHTTP_READ_TIMEOUT = 60L

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideHttpClient())
            .build().create(AuthApi::class.java)
    }

    private fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor{ chain ->
                chain.proceed(chain
                    .request().run {
                        val url = url()
                            .newBuilder()
                            .build()

                        newBuilder()
                            .url(url)
                            .build()
                    }
                )
            }
            .connectTimeout(OKHTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(OKHTTP_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OKHTTP_READ_TIMEOUT, TimeUnit.SECONDS)
            .apply {
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                        addInterceptor(this)
                    }
                }
            }
            .build()
    }
}