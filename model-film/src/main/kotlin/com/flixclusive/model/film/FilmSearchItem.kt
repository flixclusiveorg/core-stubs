package com.flixclusive.model.film

import com.flixclusive.model.film.FilmIdSource.IMDB
import com.flixclusive.model.film.FilmIdSource.TMDB
import com.flixclusive.model.film.util.DateAsLongSerializer
import com.flixclusive.model.film.util.FilmType
import com.flixclusive.model.film.util.dateFromYear
import com.flixclusive.model.film.util.parseDate
import kotlinx.serialization.Serializable
import java.util.Date

/**
 * Represents a film search result item.
 *
 * @property id The unique identifier for the film.
 * @property title The title of the film.
 * @property providerId The provider id of the provider this film came from.
 * @property homePage The URL of the film's home page.
 * @property posterImage The URL of the film's poster image.
 * @property filmType The type of the film. Defaults to [FilmType.MOVIE]. For more, see [FilmType].
 * @property backdropImage The URL of the film's backdrop image.
 * @property tmdbId The TMDB ID of the film.
 * @property imdbId The IMDB ID of the film.
 * @property rating The rating of the film. Defaults to 0.0.
 * @property language The language of the film.
 * @property adult Whether the film is marked as adult. Defaults to false.
 * @property overview The overview of the film.
 * @property releaseDate The release date of the film.
 * @property year The release year of the film.
 * @property logoImage The URL of the film's logo image.
 * @property genres A list of genres associated with the film.
 * @property genreIds A list of genre IDs associated with the film.
 * @property voteCount The vote count of the film.
 * @property customProperties A map of custom properties associated with the film. Add any properties that your response/resource needs. Also, serialize the value of the property to string.
 *
 * @see Film
 */
@Serializable
data class FilmSearchItem(
    override val id: String,
    override val providerId: String,
    /** @see FilmType */
    override val filmType: FilmType,
    override val homePage: String?,
    override val title: String,
    override val posterImage: String?,
    override val adult: Boolean = false,
    override val backdropImage: String? = null,
    override val externalIds: Map<FilmIdSource, String> = emptyMap(),
    @Serializable(DateAsLongSerializer::class) override val releaseDate: Date? = null,
    override val rating: Double? = null,
    override val language: String? = null,
    override val overview: String? = null,
    override val logoImage: String? = null,
    override val genres: List<Genre> = emptyList(),
    override val customProperties: Map<String, String?> = emptyMap(),
    val voteCount: Int = 0,
    val genreIds: List<Int> = emptyList(),
) : Film() {

    @Deprecated(
        message = "Use sourceIds[FilmIdSource.IMDB] instead.",
        replaceWith = ReplaceWith("sourceIds[FilmIdSource.IMDB]"),
    )
    override val imdbId: String?
        get() = externalIds[IMDB]

    @Deprecated(
        message = "Use sourceIds[FilmIdSource.TMDB]?.toIntOrNull() instead.",
        replaceWith = ReplaceWith("sourceIds[FilmIdSource.TMDB]?.toIntOrNull()"),
    )
    override val tmdbId: Int?
        get() = externalIds[TMDB]?.toIntOrNull()

    @Deprecated(
        message = "Legacy constructor without sourceIds is deprecated. Use sourceIds and Date releaseDate instead.",
        replaceWith = ReplaceWith(
            "FilmSearchItem(id, providerId, filmType, homePage, title, posterImage, adult = adult, backdropImage = backdropImage, sourceIds = sourceIds, releaseDate = releaseDate, rating = rating, language = language, overview = overview, logoImage = logoImage, genres = genres, customProperties = customProperties, voteCount = voteCount, genreIds = genreIds)"
        ),
        level = DeprecationLevel.WARNING,
    )
    constructor(
        id: String?,
        providerId: String,
        filmType: FilmType,
        homePage: String?,
        title: String,
        posterImage: String?,
        adult: Boolean = false,
        backdropImage: String? = null,
        imdbId: String? = null,
        tmdbId: Int? = null,
        releaseDate: String? = null,
        rating: Double? = null,
        language: String? = null,
        overview: String? = null,
        year: Int? = null,
        logoImage: String? = null,
        genres: List<Genre> = emptyList(),
        customProperties: Map<String, String?> = emptyMap(),
        voteCount: Int = 0,
        genreIds: List<Int> = emptyList(),
    ) : this(
        id = id ?: tmdbId?.toString() ?: imdbId ?: "",
        providerId = providerId,
        filmType = filmType,
        homePage = homePage,
        title = title,
        posterImage = posterImage,
        adult = adult,
        backdropImage = backdropImage,
        externalIds = buildMap {
            tmdbId?.let { put(TMDB, it.toString()) }
            imdbId?.let { put(IMDB, it) }
        },
        releaseDate = parseDate(releaseDate) ?: year?.let(::dateFromYear),
        rating = rating,
        language = language,
        overview = overview,
        logoImage = logoImage,
        genres = genres,
        customProperties = customProperties,
        voteCount = voteCount,
        genreIds = genreIds,
    )
}