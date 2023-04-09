package com.example.rickrolled.di

import com.core.network.repository.CharacterRepository
import com.core.network.repository.EpisodeRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { CharacterRepository(get()) }
    single { EpisodeRepository(get()) }
}