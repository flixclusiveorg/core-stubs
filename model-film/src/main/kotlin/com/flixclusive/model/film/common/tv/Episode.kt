package com.flixclusive.model.film.common.tv

import com.flixclusive.model.film.util.DateAsLongSerializer
import com.flixclusive.model.film.util.formatDate
import com.flixclusive.model.film.util.parseDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date
import java.util.Objects

/**
 * Data class representing an episode of a TV show.
 *
 * This class is serializable using both Kotlin serialization and Java serialization.
 *
 * @property id The unique identifier of the episode.
 * @property overview A brief summary of the episode.
 * @property runtime The duration of the episode in minutes.
 * @property number The episode number within its season.
 * @property title The title of the episode.
 * @property airDate The original air date of the episode (optional).
 * @property season The season number the episode belongs to.
 * @property image The path to an image associated with the episode (optional).
 * @property rating The average rating of the episode (optional).
 * @property releaseDate The formatted release date of the episode.
 */
@Serializable
data class Episode(
    val id: String = "",
    val overview: String = "",
    val runtime: Int? = null,
    @SerialName("episode_number") val number: Int = 0,
    @SerialName("name") val title: String = "",
    @Serializable(DateAsLongSerializer::class)
    @SerialName("air_date") val airDate: Date? = null,
    @SerialName("season_number") val season: Int = 0,
    @SerialName("still_path") val image: String? = null,
    @SerialName("vote_average") val rating: Double? = null
) : java.io.Serializable {
    /**
     * The formatted release date of the episode. Attempts to format the `airDate`
     * and falls back to the original `airDate` or an empty string if formatting fails.
     */
    val releaseDate: String
        get() = try {
            formatDate(airDate)
        } catch (_: Throwable) {
            ""
        }

    @Deprecated(
        message = "String airDate constructor is deprecated. Use Date airDate instead.",
        replaceWith = ReplaceWith("Episode(id, overview, runtime, number, title, airDate, season, image, rating)"),
        level = DeprecationLevel.WARNING,
    )
    constructor(
        id: String = "",
        overview: String = "",
        runtime: Int? = null,
        number: Int = 0,
        title: String = "",
        airDate: String? = null,
        season: Int = 0,
        image: String? = null,
        rating: Double? = null,
    ) : this(
        id = id,
        overview = overview,
        runtime = runtime,
        number = number,
        title = title,
        airDate = parseDate(airDate),
        season = season,
        image = image,
        rating = rating,
    )

    /**
     * Calculates the hash code based on the episode's season, number, and ID.
     *
     * @return The hash code of the episode.
     */
    override fun hashCode(): Int {
        return Objects.hash(season, number, id)
    }

    /**
     * Checks if this episode is equal to another object.
     *
     * Two episodes are considered equal if they have the same season, number, and ID.
     *
     * @param other The object to compare to.
     * @return `true` if the episodes are equal, `false` otherwise.
     */
    override fun equals(other: Any?): Boolean {
        if (other !is Episode)
            return false

        return season == other.season && number == other.number && id == other.id
    }
}