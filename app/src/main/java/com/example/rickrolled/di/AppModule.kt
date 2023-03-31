package com.example.rickrolled.di

import androidx.room.Room
import com.example.rickrolled.data.local.AppDatabase
import com.example.rickrolled.data.remote.service.CharacterService
import com.example.rickrolled.data.remote.repository.CharacterRepository
import com.example.rickrolled.data.remote.repository.EpisodeRepository
import com.example.rickrolled.data.remote.service.EpisodeService
import com.example.rickrolled.ui.screen.character_details.CharacterDetailsViewModel
import com.example.rickrolled.ui.screen.character_list.CharacterListViewModel
import com.example.rickrolled.ui.screen.favorites.FavoritesViewModel
import com.example.rickrolled.utils.Constants
import com.example.rickrolled.utils.NetworkConnectivityObserver
import com.example.rickrolled.utils.SharedPreferencesExtensions
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "rickrolled.db"
        ).build()
    }

    single { SharedPreferencesExtensions(androidContext()) }
    single { NetworkConnectivityObserver(androidContext()) }
}