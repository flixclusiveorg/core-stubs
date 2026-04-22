package com.flixclusive.model.film.util

/**
 * Represents the type of a film.
 *
 * @property type The string representation of the film type.
 *
 * @see MOVIE
 * @see TV_SHOW
 */
enum class FilmType(
    val type: String
) {
    /**
     * Represents a movie.
     */
    MOVIE(type = "movie"),

    /**
     * Represents a TV show.
     */
    TV_SHOW(type = "tv");

    val isMovie: Boolean get() = this == MOVIE
    val isTvShow: Boolean get() = this == TV_SHOW

    /**
     * Companion object for [FilmType].
     */
    companion object {
        /**
         * Converts a string to a [FilmType].
         *
         * @return The corresponding [FilmType], or throws an [IllegalStateException] if the string is invalid.
         */
        fun String?.toFilmType(): FilmType {
            var result = entries.find { it.type == this }
            if (result == null) {
                result = when (this?.lowercase()) {
                    "tv series", "tv", "show", "tv show", "tvshow" -> TV_SHOW
                    "movie" -> MOVIE
                    else -> throw IllegalStateException("Invalid film type: $this")
                }
            }

            return result
        }
    }
}