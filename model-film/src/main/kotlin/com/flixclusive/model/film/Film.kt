package com.flixclusive.model.film

import com.flixclusive.model.film.util.DateAsLongSerializer
import com.flixclusive.model.film.util.FilmType
import com.flixclusive.model.film.util.extractYear
import com.flixclusive.model.film.util.formatDate
import com.flixclusive.model.film.util.isDateInFuture
import kotlinx.serialization.Serializable
import java.util.Date

/** The default film source name. Defaults to TMDB. */
@Deprecated(
    message = "The app will now have its TMDB support separated as a provider. " +
            "This constant is no longer relevant and will be removed in a future version.",
    level = DeprecationLevel.WARNING
)
const val DEFAULT_FILM_SOURCE_NAME = "TMDB"

/**
 * Represents the release status of a film.
 *
 * @see RELEASED
 * @see COMING_SOON
 * @see UNKNOWN
 */
enum class FilmReleaseStatus {
    /**
     * The film has been released.
     */
    RELEASED,

    /**
     * The film is coming soon.
     */
    COMING_SOON,

    /**
     * The release status of the film is unknown.
     */
    UNKNOWN
}

/**
 * An abstract representation of a film.
 *
 * @property id The stable unique ID of the film. Providers must always supply this value.
 * @property filmType The type of film. Could either be [FilmType.MOVIE] or [FilmType.TV_SHOW].
 * @property overview An overview, sypnosis or description of the film.
 * @property adult Indicates whether the film is for adults only.
 * @property runtime The runtime of the film in minutes.
 * @property genres A list of genres associated with the film.
 * @property recommendations A list of recommended films.
 * @property title The title of the film.
 * @property language The language of the film.
 * @property rating The average rating of the film.
 * @property backdropImage The URL to the backdrop image of the film (optional).
 * @property posterImage The URL to the poster image of the film (optional).
 * @property homePage The URL to the home page of the film (optional).
 * @property providerId The provider id of the provider this film came from.
 * @property externalIds A map of external IDs keyed by [FilmIdSource].
 * @property imdbId The IMDB ID of the film (optional).
 * @property tmdbId The TMDB ID of the film (optional).
 * @property logoImage The URL to the logo image of the film (optional).
 * @property parsedReleaseDate Deprecated pre-formatted release date string.
 * @property releaseDate The release date of the film.
 * @property year The release year derived from [releaseDate].
 * @property releaseStatus The release status of the film. See [FilmReleaseStatus].
 * @property identifier A deprecated alias of [id].
 * @property year The year of the film's release, extracted from the release date.
 * @property customProperties A map of custom properties associated with the film. Add any properties that your response/resource needs. Also, serialize the value of the property to string.
 *
 * @see FilmMetadata
 * @see FilmSearchItem
 */
@Serializable
abstract class Film : java.io.Serializable {
    abstract val id: String
    /** @see FilmType */
    abstract val filmType: FilmType
    abstract val overview: String?
    abstract val adult: Boolean
    abstract val title: String
    abstract val language: String?
    abstract val rating: Double?
    abstract val backdropImage: String?
    abstract val posterImage: String?
    abstract val homePage: String?

    @Serializable(DateAsLongSerializer::class) abstract val releaseDate: Date?
    abstract val customProperties: Map<String, String?>

    open val recommendations: List<FilmSearchItem>
        get() = emptyList()
    abstract val providerId: String

    open val externalIds: Map<FilmIdSource, String>
        get() = emptyMap()

    @Deprecated(
        message = "Use sourceIds[FilmIdSource.IMDB] instead.",
        replaceWith = ReplaceWith("sourceIds[FilmIdSource.IMDB]"),
    )
    open val imdbId: String?
        get() = externalIds[FilmIdSource.IMDB]

    @Deprecated(
        message = "Use sourceIds[FilmIdSource.TMDB]?.toIntOrNull() instead.",
        replaceWith = ReplaceWith("sourceIds[FilmIdSource.TMDB]?.toIntOrNull()"),
    )
    open val tmdbId: Int?
        get() = externalIds[FilmIdSource.TMDB]?.toIntOrNull()

    open val logoImage: String?
        get() = null

    @Deprecated(
        message = "parsedReleaseDate is deprecated. Use releaseDate directly.",
        replaceWith = ReplaceWith("releaseDate"),
        level = DeprecationLevel.WARNING,
    )
    open val parsedReleaseDate: String?
        get() = try {
            formatDate(releaseDate)
        } catch (_: Throwable) {
            null
        }

    @Deprecated(
        message = "year is deprecated. Derive year from releaseDate instead.",
        replaceWith = ReplaceWith("releaseDate"),
        level = DeprecationLevel.WARNING,
    )
    open val year: Int?
        get() = releaseDate?.extractYear()

    open val runtime: Int?
        get() = null
    open val genres: List<Genre>
        get() = emptyList()


    /** @see FilmReleaseStatus */
    open val releaseStatus: FilmReleaseStatus
        get() = try {
            val date = releaseDate
            when {
                date == null -> FilmReleaseStatus.UNKNOWN
                !isDateInFuture(date) -> FilmReleaseStatus.RELEASED
                else -> FilmReleaseStatus.COMING_SOON
            }
        } catch (_: Throwable) {
            FilmReleaseStatus.UNKNOWN
        }

    @Deprecated(
        message = "Use id instead. Film.id is now required and stable.",
        replaceWith = ReplaceWith("id"),
    )
    val identifier: String
        get() = id
}


/*
Same properties with data types:
  - backdrop_path
  - original_language
  - overview
  - adult
  - id
  - vote_count
  - vote_average
  - poster_path
  - popularity
* */