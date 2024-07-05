package com.example.flickrimagesearch.ui.view.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.flickrimagesearch.data.FlickrItem
import com.example.flickrimagesearch.ui.theme.FlickrFinderTheme
import com.example.flickrimagesearch.ui.view.details.DetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    private var feeds: List<FlickrItem> by mutableStateOf(emptyList())
    private var isLoading: Boolean by mutableStateOf(false)
    private var isInitialState: Boolean by mutableStateOf(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrFinderTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainView(
                        flickrItems = feeds,
                        isLoading = isLoading,
                        isInitialState = isInitialState,
                        onSearch = {
                            mainViewModel.getFeeds(it)
                        },
                        onItemSelected = {
                            openDetails(it)
                        }
                    )
                }
            }
        }
        setupViewModelObservers()
    }

    private fun openDetails(it: FlickrItem) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("flickrItem", it)
        startActivity(intent)
    }

    private fun setupViewModelObservers() {
        mainViewModel.feed.observe(this) {
            feeds = it
            isInitialState = false
        }
        mainViewModel.loading.observe(this) {
            isLoading = it
        }
    }
}