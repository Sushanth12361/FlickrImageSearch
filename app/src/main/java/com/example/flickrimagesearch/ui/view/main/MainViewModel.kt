package com.example.flickrimagesearch.ui.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickrimagesearch.data.FlickrItem
import com.example.flickrimagesearch.repository.FlickrRepository
import com.example.flickrimagesearch.util.ApiResult
import com.example.flickrimagesearch.util.CoroutineContextProvider
import kotlinx.coroutines.launch

class MainViewModel(
    private val dispatcher: CoroutineContextProvider,
    private val flickrRepository: FlickrRepository
): ViewModel() {
    private val _feeds = MutableLiveData<List<FlickrItem>>()
    val feed: LiveData<List<FlickrItem>> get() = _feeds

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun getFeeds(search: String) {
        if(search.isEmpty()) {
            _feeds.postValue(emptyList())
            return
        }
        _loading.postValue(true)
        viewModelScope.launch(dispatcher.IO) {
            when (
                val result = flickrRepository.getFeeds(search)
            ) {
                is ApiResult.Success -> {
                    _loading.postValue(false)
                    result.result?.items?.let {
                        _feeds.postValue(it)
                    }
                }
                is ApiResult.Error -> {
                    _loading.postValue(false)
                }
            }
        }
    }
}