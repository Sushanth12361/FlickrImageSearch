package com.example.flickrimagesearch

import android.app.Application
import com.example.flickrimagesearch.di.apiModule
import com.example.flickrimagesearch.di.coroutineContextProviderModule
import com.example.flickrimagesearch.di.remoteModule
import com.example.flickrimagesearch.di.repositoryModule
import com.example.flickrimagesearch.di.utilModule
import com.example.flickrimagesearch.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class InitApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@InitApplication)
            modules(
                listOf(
                    viewModelModule,
                    apiModule,
                    repositoryModule,
                    utilModule,
                    remoteModule,
                    coroutineContextProviderModule
                )
            )
        }
    }
}