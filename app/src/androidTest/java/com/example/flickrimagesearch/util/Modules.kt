package com.example.flickrimagesearch.util

import com.example.flickrimagesearch.repository.FlickrRepository
import com.example.flickrimagesearch.ui.view.details.DetailsViewModel
import com.example.flickrimagesearch.ui.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class Modules(
    dispatcher: CoroutineContextProvider,
    flickrRepository: FlickrRepository
) {
    private val viewModelModule = module {
        viewModel {
            MainViewModel(
                dispatcher = dispatcher,
                flickrRepository = flickrRepository
            )
        }
        viewModel {
            DetailsViewModel(
                dispatcher = dispatcher
            )
        }
    }

    private val repositoryModule = module {
        single<FlickrRepository> {
            FlickrRepositoryMock()
        }
    }

    fun getList(): List<Module> {
        return listOf(
            viewModelModule,
            repositoryModule
        )
    }
}