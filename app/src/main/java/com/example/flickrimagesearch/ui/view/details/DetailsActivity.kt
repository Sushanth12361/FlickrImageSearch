package com.example.flickrimagesearch.ui.view.details

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.flickrimagesearch.data.FlickrItem
import com.example.flickrimagesearch.ui.theme.FlickrFinderTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : ComponentActivity() {
    private val detailsViewModel: DetailsViewModel by viewModel()
    private val flickrItem: FlickrItem by lazy { getItemFromIntent() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrFinderTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    DetailsView(
                        flickrItem = flickrItem,
                        onBackClick = { finish() },
                        onShareClick = { shareImage() }
                    )
                }
            }
        }
        setupViewModelObservers()
    }

    private fun shareImage() {
        detailsViewModel.generateImageLocally(this, flickrItem.media.url)
    }

    @Suppress("DEPRECATION")
    private fun getItemFromIntent() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getSerializableExtra("flickrItem", FlickrItem::class.java) as FlickrItem
    } else {
        intent.getSerializableExtra("flickrItem") as FlickrItem
    }

    private fun setupViewModelObservers() {
        detailsViewModel.imageGenerated.observe(this) {
            try {

                // Create a content URI for the image using the FileProvider
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "image/png"
                shareIntent.putExtra(Intent.EXTRA_STREAM, it)
                shareIntent.putExtra(Intent.EXTRA_TITLE, flickrItem.title)
                shareIntent.putExtra(Intent.EXTRA_TEXT, flickrItem.author)
                startActivity(Intent.createChooser(shareIntent, "Share Image"))
            } catch (e: Exception) {
               e.printStackTrace()
            }
        }
    }
}