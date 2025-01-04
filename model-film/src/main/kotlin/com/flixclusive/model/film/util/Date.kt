package com.flixclusive.model.film.util

import java.text.SimpleDateFormat
import java.util.Calendar
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


/**
 * Determines whether the given date string represents a date in the future.
 *
 * @param dateString The date string to check. It should be in the format "yyyy-MM-dd" or "MMMM d, yyyy".
 * @return `true` if the date is in the future, `false` otherwise.
 */
fun isDateInFuture(dateString: String): Boolean {
    val locale = Locale.US

    val format = if(dateString.contains(",")) {
        "MMMM d, yyyy"
    } else if(dateString.contains("-")) {
        "yyyy-MM-dd"
    } else ""

    val formatter = SimpleDateFormat(format, locale)
    val currentDate = Calendar.getInstance().time
    val date = formatter.parse(dateString)

    return date?.after(currentDate) ?: false
}

/**
 * Extracts the year from a string using a regular expression.
 *
 * @return The extracted year as an integer, or null if no match is found.
 * */
fun String.extractYear(): Int? {
    val yearPattern = """\b(\d{4})\b""".toRegex()
    return yearPattern.find(this)?.value?.toIntOrNull()
}