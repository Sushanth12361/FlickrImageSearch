package com.example.flickrimagesearch.ui.view.details

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickrimagesearch.util.CoroutineContextProvider
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import java.io.File

class DetailsViewModel(
    private val dispatcher: CoroutineContextProvider
): ViewModel() {
    private val _imageGenerated = MutableLiveData<Uri>()
    val imageGenerated: LiveData<Uri> get() = _imageGenerated

    fun generateImageLocally(context: Context, url: String) {
        viewModelScope.launch(dispatcher.IO) {
            val bitmap = getImageBitmap(context, url)
            val imageFile = File(context.cacheDir, "image.png")
            imageFile.outputStream().use { out ->
                bitmap?.compress(Bitmap.CompressFormat.PNG, 100, out)
            }
            val contentUri = FileProvider.getUriForFile(
                context,
                "com.andrefpc.flickrfinder.fileprovider",
                imageFile
            )
            _imageGenerated.postValue(contentUri)
        }
    }

    private fun getImageBitmap(context: Context, url: String): Bitmap? {
        val glideRequest = Glide.with(context)
            .asBitmap()
            .load(url)
        return glideRequest.submit().get()
    }
}