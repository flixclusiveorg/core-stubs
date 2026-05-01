package com.flixclusive.model.media

import com.flixclusive.model.media.common.Cast
import com.flixclusive.model.media.common.Company
import com.flixclusive.model.media.common.Genre
import com.flixclusive.model.media.common.MediaIdSource
import com.flixclusive.model.media.common.MediaType
import com.flixclusive.model.media.common.tv.Episode
import com.flixclusive.model.media.common.tv.Season
import kotlinx.serialization.Serializable

/**
 * Represents detailed information of a show.
 *
 * @property id The unique identifier of the show.
 * @property title The title of the show.
 * @property providerId The provider id of the provider this film came from.
 * @property homePage The home page of the show.
 * @property posterImage The poster image of the show.
 * @property backdropImage The backdrop image of the show.
 * @property rating The rating of the show.
 * @property language The language of the show.
 * @property adult Whether the show is marked as adult.
 * @property overview The overview of the show.
 * @property releaseDate The release date of the show.
 * @property tagLine The tag line of the show.
 * @property genres The genres of the show.
 * @property networks The networks that aired the show.
 * @property seasons The seasons of the show.
 * @property totalEpisodes The total number of episodes in the show.
 * @property totalSeasons The total number of seasons in the show.
 * @property runtime The runtime of the show.
 * @property customProperties A map of custom properties associated with the film. Add any properties that your response/resource needs. Also, serialize the value of the property to string.
 *
 * @see MediaMetadata
 * @see Movie
 * */
@Serializable
data class Show(
    override val id: String,
    override val title: String,
    override val providerId: String,
    override val posterImage: String?,
    override val externalIds: Map<MediaIdSource, String> = emptyMap(),
    override val customProperties: Map<String, String?> = emptyMap(),
    override val recommendations: List<MediaMetadata> = emptyList(),
    override val genres: List<Genre> = emptyList(),
    override val casts: List<Cast> = emptyList(),
    override val backdropImage: String? = null,
    override val logoImage: String? = null,
    override val homePage: String? = null,
    override val language: String? = null,
    override val releaseDate: Long? = null,
    override val rating: Double? = null,
    override val adult: Boolean = false,
    override val overview: String? = null,
    override val tagLine: String? = null,
    override val runtime: Int? = null,
    override val certification: String? = null,

    val networks: List<Company> = emptyList(),
    val seasons: List<Season>,
    val totalEpisodes: Int,
    val totalSeasons: Int,
    val lastAirDate: Long? = null,
) : MediaMetadata {
    override val type: MediaType get() = MediaType.SHOW

    private val seasonMap by lazy {
        seasons.associateBy { it.number }
    }

    /**
     * Retrieves a season by its number.
     *
     * Does a binary search on the seasons list, so it will sort the seasons by
     * number on the first calland cache the sorted list for subsequent calls.
     *
     * @param number The season number to retrieve.
     * @return The season with the specified number, or null if not found.
     * */
    fun getSeason(number: Int): Season? {
        return seasonMap[number]
    }

    /**
     * Retrieves an episode by its season and episode number.
     *
     * @param season The season number of the episode to retrieve.
     * @param episode The episode number to retrieve.
     *
     * @return The episode with the specified season and episode number, or null if not found
     * */
    fun getEpisode(season: Int, episode: Int): Episode? {
        return seasonMap[season]?.getEpisode(episode)
    }

    /**
     * Retrieves an episode by its season and episode number.
     *
     * @param season The season of the episode to retrieve.
     * @param episode The episode number to retrieve.
     *
     * @return The episode with the specified season and episode number, or null if not found
     * */
    fun getEpisode(season: Season, episode: Int): Episode? {
        return getEpisode(season.number, episode)
    }

}
