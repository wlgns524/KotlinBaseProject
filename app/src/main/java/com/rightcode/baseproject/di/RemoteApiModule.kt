package com.rightcode.baseproject.di

import android.os.Build
import com.rightcode.baseproject.Features
import com.rightcode.baseproject.data.remote.api.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RemoteApiModule {

    @Provides
    @Singleton
    fun provideRetrofitService(): ApiService = Retrofit.Builder()
        .baseUrl(ApiService.BASE_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        )
        .client(providesOkHttpClient(providesLoggingInterceptor(), providesOkhttpInterceptor()))
        .build()
        .create(ApiService::class.java)


    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        if (Features.TEST_ONLY && Features.SHOW_NETWORK_LOG) {
            return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }


    @Provides
    @Singleton
    fun providesOkhttpInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val original: Request = chain.request()
            val requestBuilder: Request.Builder = original.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("X-08liter-language", Locale.getDefault().getLanguage().toLowerCase())
                .addHeader("X-08liter-os-version", Build.VERSION.RELEASE)
                .addHeader("X-08liter-model", Build.MODEL)
                .addHeader("X-08liter-platform", "Android")
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
    }


    @Provides
    @Singleton
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        interceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(interceptor)
            .connectTimeout(3, TimeUnit.MINUTES)
            .callTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .build()
    }

}