package com.flixclusive.model.media

import com.flixclusive.model.media.common.Cast
import com.flixclusive.model.media.common.Genre
import com.flixclusive.model.media.common.MediaIdSource
import com.flixclusive.model.media.common.MediaType
import com.flixclusive.model.media.util.isDateInFuture

/**
 * Represents the release status of a media (movie/shows).
 *
 * @see RELEASED
 * @see COMING_SOON
 * @see UNKNOWN
 */
enum class MediaReleaseStatus {
    /**
     * The media (movie/shows) has been released.
     */
    RELEASED,

    /**
     * The media (movie/shows) is coming soon.
     */
    COMING_SOON,

    /**
     * The release status of the media (movie/shows) is unknown.
     */
    UNKNOWN
}

/**
 * A representation of a media metadata (movie/shows).
 *
 * @property id The stable unique ID of the media (movie/shows). Providers must always supply this value.
 * @property overview An overview, synopsis or description of the media (movie/shows).
 * @property adult Indicates whether the media (movie/shows) is for adults only.
 * @property runtime The runtime of the media (movie/shows) in minutes.
 * @property genres A list of genres associated with the media (movie/shows).
 * @property recommendations A list of recommended media (movie/shows)s.
 * @property title The title of the media (movie/shows).
 * @property language The language of the media (movie/shows).
 * @property rating The average rating of the media (movie/shows).
 * @property backdropImage The URL to the backdrop image of the media (movie/shows) (optional).
 * @property posterImage The URL to the poster image of the media (movie/shows) (optional).
 * @property homePage The URL to the home page of the media (movie/shows) (optional).
 * @property providerId The provider id of the provider this media (movie/shows) came from.
 * @property externalIds A map of external IDs keyed by [com.flixclusive.model.media.common.MediaIdSource].
 * @property logoImage The URL to the logo image of the media (movie/shows) (optional).
 * @property releaseDate The release date of the media (movie/shows).
 * @property releaseStatus The release status of the media (movie/shows). See [MediaReleaseStatus].
 * @property casts A list of cast members associated with the media (movie/shows).
 * @property certification The certification of the media (movie/shows) (optional).
 * @property tagLine The tag line of the media (movie/shows) (optional).
 * @property isMovie Indicates whether the media is a movie.
 * @property isShow Indicates whether the media is a show.
 * @property type The type of the media (movie/shows).
 * @property customProperties A map of custom properties associated with the media (movie/shows). Add any properties that your response/resource needs. Also, serialize the value of the property to string.
 */
interface MediaMetadata : java.io.Serializable {
    val id: String
    val providerId: String
    val overview: String?
    val adult: Boolean
    val title: String
    val language: String?
    val rating: Double?
    val backdropImage: String?
    val posterImage: String?
    val genres: List<Genre>
    val releaseDate: Long?
    val type: MediaType
    
    val customProperties: Map<String, String?> get() = emptyMap()
    val externalIds: Map<MediaIdSource, String> get() = emptyMap()
    val recommendations: List<MediaMetadata> get() = emptyList()
    val casts: List<Cast> get() = emptyList()

    val certification: String? get() = null
    val tagLine: String? get() = null
    val logoImage: String? get() = null
    val runtime: Int? get() = null
    val homePage: String? get() = null


    /** @see MediaReleaseStatus */
    val releaseStatus: MediaReleaseStatus
        get() = try {
            val date = releaseDate
            when {
                date == null -> MediaReleaseStatus.UNKNOWN
                !isDateInFuture(date) -> MediaReleaseStatus.RELEASED
                else -> MediaReleaseStatus.COMING_SOON
            }
        } catch (_: Throwable) {
            MediaReleaseStatus.UNKNOWN
        }


    val isMovie get() = this is Movie || type.isMovie
    val isShow get() = this is Show || type.isMovie
}