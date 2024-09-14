package com.flixclusive.model.film.common.tv

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * Data class representing a season of a TV show.
 *
 * This class is serializable using both Kotlin serialization and Java serialization.
 *
 * @property overview A brief summary of the season (optional).
 * @property name The name or title of the season.
 * @property episodes A list of episodes in the season.
 * @property airDate The air date of the season (optional), may be in an inconsistent format.
 * @property episodeCount The number of episodes in the season (optional).
 * @property rating The average rating of the season (optional).
 * @property number The season number.
 * @property image The path to an image associated with the season (optional).
 * @property isUnreleased Indicates whether the season is unreleased (has no air date, episode count, or rating).
 */
@Serializable
data class Season(
    val overview: String? = null,
    val name: String = "",
    val episodes: List<Episode> = emptyList(),
    @SerializedName("air_date") private val airDate: String? = null,
    @SerializedName("episode_count") private val episodeCount: Int? = null,
    @SerializedName("vote_average") val rating: Double? = null,
    @SerializedName("season_number") val number: Int = 0,
    @SerializedName("poster_path") val image: String? = null,
) : java.io.Serializable {
    /**
     * Indicates whether the season is unreleased. A season is considered unreleased
     * if it has no air date, episode count, and has a rating of 0.0.
     */
    val isUnreleased: Boolean
        get() = airDate == null && episodeCount == 0 && rating == 0.0
}

