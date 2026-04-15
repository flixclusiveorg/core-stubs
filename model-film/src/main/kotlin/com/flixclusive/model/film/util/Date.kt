package com.flixclusive.model.film.util

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


internal fun dateFromYear(year: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.clear()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, Calendar.JANUARY)
    calendar.set(Calendar.DAY_OF_MONTH, 1)
    return calendar.time
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
 * @param date The date to check.
 * @return `true` if the date is in the future, `false` otherwise.
 */
fun isDateInFuture(date: Date): Boolean {
    val currentDate = Calendar.getInstance().time
    return date.after(currentDate)
}


@Deprecated(
    message = "Use isDateInFuture(Date) instead.",
    replaceWith = ReplaceWith("parseDate(dateString)?.let(::isDateInFuture) ?: false"),
    level = DeprecationLevel.WARNING,
)
fun isDateInFuture(dateString: String): Boolean {
    return parseDate(dateString)?.let(::isDateInFuture) ?: false
}


/**
 * Extracts the year from a [Date].
 *
 * @return The extracted year as an integer.
 * */
fun Date.extractYear(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.YEAR)
}


@Deprecated(
    message = "Use Date.extractYear() instead.",
    replaceWith = ReplaceWith("parseDate(this)?.extractYear()"),
    level = DeprecationLevel.WARNING,
)
fun String.extractYear(): Int? {
    return parseDate(this)?.extractYear()
}