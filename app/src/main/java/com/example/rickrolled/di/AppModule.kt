package com.example.rickrolled.di

import androidx.room.Room
import com.core.common.FavoritesSharedPreferences
import com.core.common.SharedPreferencesExtensions
import com.core.network.local.AppDatabase
import com.example.rickrolled.utils.NetworkConnectivityObserver
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "rickrolled.db"
        ).build()
    }

    single { SharedPreferencesExtensions(androidContext()) }
    single { FavoritesSharedPreferences(get()) }
    single { NetworkConnectivityObserver(androidContext()) }
}