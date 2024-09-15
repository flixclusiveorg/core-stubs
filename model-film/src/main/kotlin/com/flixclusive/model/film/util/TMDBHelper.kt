package com.flixclusive.model.film.util

import com.flixclusive.model.film.FilmSearchItem
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.regex.Pattern

/**
 * Filters out films that have not yet been released and have no poster image.
 * */
fun List<FilmSearchItem>.filterOutUnreleasedFilms()
    = filterNot {
        try {
            isDateInFuture(it.parsedReleaseDate!!)
        } catch (_: Exception) {
            false
        } || it.posterImage.isNullOrEmpty()
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
 * Replaces the type in the URL with the given type.
 *
 * @param type The type to replace the current type with.
 *
 * @return The URL with the replaced type.
 * */
fun String.replaceTypeInUrl(type: String): String {
    val pattern = Pattern.compile("(?<=/)[a-z]+(?=\\?)")
    val matcher = pattern.matcher(this)

    if (matcher.find()) {
        return matcher.replaceFirst(type)
    }

    return this
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