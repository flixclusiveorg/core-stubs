package com.flixclusive.model.film.util

import com.flixclusive.model.film.FilmSearchItem
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