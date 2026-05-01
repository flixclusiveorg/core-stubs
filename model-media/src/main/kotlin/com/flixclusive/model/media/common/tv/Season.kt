package com.flixclusive.model.media.common.tv

import kotlinx.serialization.Serializable

/**
 * Data class representing a season of a TV show.
 *
 * This class is serializable using both Kotlin serialization and Java serialization.
 *
 * @property overview A brief summary of the season (optional).
 * @property title The name or title of the season.
 * @property episodes A list of episodes in the season.
 * @property releaseDate The air date of the season (optional), may be in an inconsistent format.
 * @property episodeCount The number of episodes in the season (optional).
 * @property rating The average rating of the season (optional).
 * @property number The season number.
 * @property image The path to an image associated with the season (optional).
 */
@Serializable
data class Season(
    val id: String,
    val number: Int,
    val episodes: List<Episode>,
    val isReleased: Boolean,
    val title: String? = null,
    val episodeCount: Int = episodes.size,
    val releaseDate: Long? = null,
    val overview: String? = null,
    val rating: Double? = null,
    val image: String? = null
) : java.io.Serializable {
    private val episodeMap by lazy {
        episodes.associateBy { it.number }
    }

    /**
     * Retrieves an episode by its episode number.
     *
     * @param episode The episode number to retrieve.
     *
     * @return The episode with the specified number, or null if not found.
     * */
    fun getEpisode(episode: Int): Episode? = episodeMap[episode]

    override fun equals(other: Any?): Boolean {
        if (other !is Season) return false

        return number == other.number && id == other.id
    }

    override fun hashCode(): Int {
        var result = number
        result = 31 * result + isReleased.hashCode()
        result = 31 * result + episodeCount
        result = 31 * result + (releaseDate?.hashCode() ?: 0)
        result = 31 * result + (rating?.hashCode() ?: 0)
        result = 31 * result + id.hashCode()
        result = 31 * result + episodes.hashCode()
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (overview?.hashCode() ?: 0)
        result = 31 * result + (image?.hashCode() ?: 0)
        return result
    }

}

