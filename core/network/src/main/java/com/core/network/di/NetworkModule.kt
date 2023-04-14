package com.core.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.core.network.utils.Constants.BASE_API_URL
import com.core.network.remote.service.CharacterService
import com.core.network.remote.service.EpisodeService
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideChuckerInterceptor(androidContext()) }
    factory { provideChuckerCollector(androidContext()) }
    factory { provideEpisodeService(get()) }
    factory { provideCharacterService(get()) }
    factory { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_API_URL).client(httpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(interceptor: ChuckerInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
}

fun provideEpisodeService(retrofit: Retrofit): EpisodeService =
    retrofit.create(EpisodeService::class.java)

fun provideCharacterService(retrofit: Retrofit): CharacterService =
    retrofit.create(CharacterService::class.java)

fun provideChuckerCollector(context: Context): ChuckerCollector = ChuckerCollector(
    context = context,
    showNotification = true,
    retentionPeriod = RetentionManager.Period.ONE_HOUR
)

fun provideChuckerInterceptor(context: Context): ChuckerInterceptor =
    ChuckerInterceptor.Builder(context)
        .collector(provideChuckerCollector(context))
        .maxContentLength(250_000L)
        .redactHeaders("Auth-Token", "Bearer")
        .build()