package com.core.network.di

import com.core.network.utils.Constants.BASE_API_URL
import com.core.network.remote.interceptor.TestInterceptor
import com.core.network.remote.service.CharacterService
import com.core.network.remote.service.EpisodeService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { TestInterceptor() }
    factory { provideEpisodeService(get()) }
    factory { provideCharacterService(get()) }
    factory { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
}

fun provideOkHttpClient(testInterceptor: TestInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(testInterceptor).build()
}

fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_API_URL).client(httpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideEpisodeService(retrofit: Retrofit): EpisodeService =
    retrofit.create(EpisodeService::class.java)

fun provideCharacterService(retrofit: Retrofit): CharacterService =
    retrofit.create(CharacterService::class.java)