package com.example.rickrolled.di

import com.example.rickrolled.ui.screen.MainScreenViewModel
import com.feature.character_details.CharacterDetailsViewModel
import com.feature.character_list.CharacterListViewModel
import com.feature.episode.EpisodeListViewModel
import com.feature.favorites.FavoritesViewModel
import com.feature.settings.SettingsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CharacterListViewModel(get(), get()) }
    viewModel { parameters -> CharacterDetailsViewModel(get(), id = parameters.get()) }
    viewModel { FavoritesViewModel(get(), get()) }
    viewModel { EpisodeListViewModel(get(), get()) }
    viewModel { SettingsScreenViewModel(get()) }
    viewModel { MainScreenViewModel(get()) }
}