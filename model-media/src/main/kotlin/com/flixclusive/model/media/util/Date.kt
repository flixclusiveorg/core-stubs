package com.flixclusive.model.media.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

private const val DATE_INPUT_FORMAT = "yyyy-MM-dd"
private const val DATE_OUTPUT_FORMAT = "MMMM d, yyyy"


internal fun parseDate(dateString: String?): Date? {
    if (dateString.isNullOrBlank()) {
        return null
    }

    val locale = Locale.US
    val patterns = listOf(DATE_INPUT_FORMAT, DATE_OUTPUT_FORMAT)

    for (pattern in patterns) {
        runCatching {
            val formatter = SimpleDateFormat(pattern, locale)
            formatter.isLenient = false
            formatter.parse(dateString)
        }.getOrNull()?.let {
            return it
        }
    }

    return null
}

internal fun formatDate(date: Date?): String {
    if (date == null) {
        return "No release date"
    }

    val outputFormat = SimpleDateFormat(DATE_OUTPUT_FORMAT, Locale.US)
    return outputFormat.format(date)
}


@Deprecated(
    message = "Use formatDate(Date?) instead.",
    replaceWith = ReplaceWith("formatDate(parseDate(dateString))"),
    level = DeprecationLevel.WARNING,
)
internal fun formatDate(dateString: String?): String {
    return formatDate(parseDate(dateString))
}


/**
 * Determines whether the given date represents a date in the future.
 *
 * @param date The date in MS since epoch to check.
 * @return `true` if the date is in the future, `false` otherwise.
 */
internal fun isDateInFuture(date: Long): Boolean {
    val date = Date(date)
    val currentDate = Calendar.getInstance().time
    return date.after(currentDate)
}