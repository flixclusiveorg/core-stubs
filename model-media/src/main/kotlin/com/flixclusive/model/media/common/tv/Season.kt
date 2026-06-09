package com.flixclusive.model.media.common.tv

import kotlinx.serialization.Serializable

/**
 * Sealed class representing a season of a TV show.
 *
 * A season can be in one of two states:
 * - [Season.Partial] — shell metadata only (no episodes). Returned by [Show.seasons] for providers
 *   that load episode data on-demand. Call `MediaMetadataProviderApi.getSeason()` to promote to [Full].
 * - [Season.Full] — complete season including the full episode list. No further API call needed.
 *
 * @property id The unique identifier of the season.
 * @property number The season number.
 * @property isReleased Whether the season has been released.
 * @property title The name or title of the season (optional).
 * @property episodeCount The total number of episodes in the season (optional).
 * @property releaseDate The air date of the season (optional).
 * @property overview A brief summary of the season (optional).
 * @property rating The average rating of the season (optional).
 * @property image The path to an image associated with the season (optional).
 */
@Serializable
sealed class Season : java.io.Serializable {
    abstract val id: String
    abstract val number: Int
    abstract val isReleased: Boolean
    abstract val title: String?
    abstract val episodeCount: Int
    abstract val releaseDate: Long?
    abstract val overview: String?
    abstract val rating: Double?
    abstract val image: String?

    /**
     * Shell season metadata with no episode data loaded.
     *
     * Returned by providers that load episodes on-demand (e.g. TMDB, Trakt).
     * To obtain the full episode list, call `MediaMetadataProviderApi.getSeason(show, this)`.
     */
    @Serializable
    data class Partial(
        override val id: String,
        override val number: Int,
        override val isReleased: Boolean,
        override val episodeCount: Int,
        override val title: String? = null,
        override val releaseDate: Long? = null,
        override val overview: String? = null,
        override val rating: Double? = null,
        override val image: String? = null,
    ) : Season() {
        override fun equals(other: Any?): Boolean {
            if (other !is Partial) return false
            return number == other.number && id == other.id
        }

        override fun hashCode(): Int {
            var result = id.hashCode()
            result = 31 * result + number
            return result
        }
    }

    /**
     * Complete season with all episode data loaded.
     *
     * Returned directly by eager providers (e.g. providers that embed episodes in `getShow()`),
     * or by `MediaMetadataProviderApi.getSeason()` after resolving a [Partial].
     */
    @Serializable
    data class Full(
        override val id: String,
        override val number: Int,
        override val isReleased: Boolean,
        override val title: String? = null,
        override val releaseDate: Long? = null,
        override val overview: String? = null,
        override val rating: Double? = null,
        override val image: String? = null,
        val episodes: List<Episode>,
    ) : Season() {
        override val episodeCount: Int get() = episodes.size
        private val episodeMap by lazy {
            episodes.associateBy { it.number }
        }

        /**
         * Retrieves an episode by its episode number.
         *
         * @param episode The episode number to retrieve.
         * @return The episode with the specified number, or null if not found.
         */
        fun getEpisode(episode: Int): Episode? = episodeMap[episode]

        override fun equals(other: Any?): Boolean {
            if (other !is Full) return false
            return number == other.number && id == other.id
        }

        override fun hashCode(): Int {
            var result = id.hashCode()
            result = 31 * result + number
            return result
        }
    }
}

