package com.example.flickrimagesearch.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FlickrMedia(
    @SerializedName("m")
    val url: String
): Serializable
