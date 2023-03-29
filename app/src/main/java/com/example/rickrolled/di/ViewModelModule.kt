package com.example.rickrolled.di

import com.example.rickrolled.ui.screen.character_details.CharacterDetailsViewModel
import com.example.rickrolled.ui.screen.character_list.CharacterListViewModel
import com.example.rickrolled.ui.screen.episode_list.EpisodeListViewModel
import com.example.rickrolled.ui.screen.favorites.FavoritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CharacterListViewModel(get(), get()) }
    viewModel { parameters -> CharacterDetailsViewModel(get(), id = parameters.get()) }
    viewModel { FavoritesViewModel(get(), get()) }
    viewModel { EpisodeListViewModel(get(), get()) }
}