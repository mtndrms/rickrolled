package com.example.rickrolled.di

import com.example.rickrolled.data.remote.service.CharacterService
import com.example.rickrolled.data.remote.service.EpisodeService
import com.example.rickrolled.utils.Constants.BASE_API_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideEpisodeService(get()) }
    factory { provideCharacterService(get()) }
    single { provideRetrofit() }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_API_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideEpisodeService(retrofit: Retrofit): EpisodeService =
    retrofit.create(EpisodeService::class.java)

fun provideCharacterService(retrofit: Retrofit): CharacterService =
    retrofit.create(CharacterService::class.java)