package com.example.flickrimagesearch.util

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Locale

object StringExtensions {
    fun String.removeHtmlTags(): String {
        val withLineBreak = this
            .replace("<br /> ", "\n\n")
            .replace("<br />", "\n\n")
            .replace("<br/>", "\n\n")
        return withLineBreak.replace("<[^>]*>".toRegex(), "").trim()
    }

    fun String.formatDate(): String {
        val from = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
        val to = SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US)
        var dateApp: String? = null
        try {
            val date = from.parse(this)
            dateApp = date?.let { to.format(it) }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        return dateApp ?: this
    }

    fun String.extractImageSize(): String {
        val pattern = "width=\"(\\d+)\".*height=\"(\\d+)\"".toRegex()
        val matchResult = pattern.find(this)
        return matchResult?.let {
            val (width, height) = it.destructured
            "Width: $width | Height: $height"
        } ?: ""
    }
}