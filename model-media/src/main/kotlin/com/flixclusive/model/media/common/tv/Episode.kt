package com.flixclusive.model.media.common.tv

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
 * @property releaseDate The original air date of the episode (optional).
 * @property season The season number the episode belongs to.
 * @property image The path to an image associated with the episode (optional).
 * @property rating The average rating of the episode (optional).
 */
@Serializable
data class Episode(
    val id: String,
    val number: Int,
    val season: Int,
    val isReleased: Boolean,
    val title: String? = null,
    val overview: String? = null,
    val runtime: Int? = null,
    val releaseDate: Long? = null,
    val image: String? = null,
    val rating: Double? = null
) : java.io.Serializable {
    override fun hashCode(): Int {
        return Objects.hash(season, number, id)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Episode)
            return false

        return season == other.season
            && number == other.number
            && id == other.id
    }
}