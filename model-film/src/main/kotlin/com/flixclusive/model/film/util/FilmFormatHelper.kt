package com.flixclusive.model.film.util

import java.text.SimpleDateFormat
import java.util.Locale


internal fun formatDate(dateString: String?): String {
    if (dateString.isNullOrEmpty()) {
        return "No release date"
    }

    val locale = Locale.US

    val inputFormat = SimpleDateFormat("yyyy-MM-dd", locale)
    val outputFormat = SimpleDateFormat("MMMM d, yyyy", locale)

    val date = inputFormat.parse(dateString)
    return date?.let {
        outputFormat.format(it)
    } ?: "No release date"
}