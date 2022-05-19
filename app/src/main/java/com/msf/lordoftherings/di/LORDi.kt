package com.msf.lordoftherings.di

import com.msf.lordoftherings.BuildConfig
import com.msf.lordoftherings.network.LoggingInterceptor
import com.msf.lordoftherings.network.Service
import com.msf.lordoftherings.network.TokenInterceptor
import com.msf.lordoftherings.repository.MoviesRepository
import com.msf.lordoftherings.repository.impl.MoviesRepositoryImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LORDi {
    val module = module {
        factory<MoviesRepository> { MoviesRepositoryImpl(service = get()) }
        factory { TokenInterceptor() }
        factory { LoggingInterceptor() }
        factory { provideOkHttpClient(
            interceptor = get(),
            tokenInterceptor = get()
        ) }
        factory { providerServiceApi(retrofit = get()) }
        factory { provideRetrofit(okHttpClient = get()) }
    }
}


fun provideOkHttpClient(interceptor: LoggingInterceptor, tokenInterceptor: TokenInterceptor): OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .addInterceptor(tokenInterceptor)
        .addInterceptor(interceptor)
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun providerServiceApi(retrofit: Retrofit): Service = retrofit.create(Service::class.java)