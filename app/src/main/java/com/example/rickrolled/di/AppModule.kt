package com.example.rickrolled.di

import androidx.room.Room
import com.core.network.local.AppDatabase
import com.example.rickrolled.utils.NetworkConnectivityObserver
import com.example.rickrolled.utils.SharedPreferencesExtensions
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
    single { NetworkConnectivityObserver(androidContext()) }
}