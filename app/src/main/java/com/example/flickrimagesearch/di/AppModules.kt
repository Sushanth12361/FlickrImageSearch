package com.example.flickrimagesearch.di

import com.example.flickrimagesearch.api.FlickrApi
import com.example.flickrimagesearch.repository.FlickrRepository
import com.example.flickrimagesearch.repository.FlickrRepositoryImpl
import com.example.flickrimagesearch.ui.view.details.DetailsViewModel
import com.example.flickrimagesearch.ui.view.main.MainViewModel
import com.example.flickrimagesearch.util.CoroutineContextProvider
import com.example.flickrimagesearch.util.api.ApiUtil
import com.example.flickrimagesearch.util.api.ApiUtilImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(
            dispatcher = get(),
            flickrRepository = get()
        )
    }

    viewModel {
        DetailsViewModel(
            dispatcher = get()
        )
    }
}

val apiModule = module {
    single { createApi<FlickrApi>(retrofit = get()) }
}

val repositoryModule = module {
    single<FlickrRepository> {
        FlickrRepositoryImpl(flickrApi = get(), apiUtil = get())
    }
}

val utilModule = module {
    single<ApiUtil> {
        ApiUtilImpl()
    }
}

val remoteModule = module {
    single { provideOkHttpClient(context = get()) }
    single { provideRetrofit(okHttpClient = get()) }
}

val coroutineContextProviderModule = module {
    single { CoroutineContextProvider() }
}