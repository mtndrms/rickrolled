package com.example.rickrolled.di

import com.example.rickrolled.data.remote.repository.CharacterRepository
import com.example.rickrolled.data.remote.repository.EpisodeRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { CharacterRepository(get(), get()) }
    single { EpisodeRepository(get()) }
}