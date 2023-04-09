package com.example.rickrolled

import android.app.Application
import com.core.network.di.networkModule
import com.example.rickrolled.di.appModule
import com.example.rickrolled.di.repositoryModule
import com.example.rickrolled.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule, networkModule, viewModelModule, repositoryModule)
        }
    }
}