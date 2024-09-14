package com.flixclusive.model.film.common.tv

import com.flixclusive.model.film.util.formatDate
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
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
 * @property airDate The original air date of the episode (optional), may be in an inconsistent format.
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
    @SerializedName("episode_number") val number: Int = 0,
    @SerializedName("name") val title: String = "",
    @SerializedName("air_date") private val airDate: String? = null,
    @SerializedName("season_number") val season: Int = 0,
    @SerializedName("still_path") val image: String? = null,
    @SerializedName("vote_average") val rating: Double? = null
) : java.io.Serializable {
    /**
     * The formatted release date of the episode. Attempts to format the `airDate`
     * and falls back to the original `airDate` or an empty string if formatting fails.
     */
    val releaseDate: String
        get() = try {
            formatDate(airDate)
        } catch (_: Throwable) {
            airDate ?: ""
        }

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