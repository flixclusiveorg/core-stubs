package com.flixclusive.model.film

import com.flixclusive.model.film.FilmIdSource.IMDB
import com.flixclusive.model.film.FilmIdSource.TMDB
import com.flixclusive.model.film.common.details.Company
import com.flixclusive.model.film.common.tv.Season
import com.flixclusive.model.film.util.DateAsLongSerializer
import com.flixclusive.model.film.util.FilmType
import com.flixclusive.model.film.util.dateFromYear
import com.flixclusive.model.film.util.parseDate
import kotlinx.serialization.Serializable
import java.util.Date

/**
 * Represents a detailed information of a TV show.
 *
 * @property id The unique identifier of the TV show.
 * @property title The title of the TV show.
 * @property providerId The provider id of the provider this film came from.
 * @property homePage The home page of the TV show.
 * @property posterImage The poster image of the TV show.
 * @property backdropImage The backdrop image of the TV show.
 * @property tmdbId The TMDB ID of the TV show.
 * @property rating The rating of the TV show.
 * @property language The language of the TV show.
 * @property adult Whether the TV show is marked as adult.
 * @property overview The overview of the TV show.
 * @property releaseDate The release date of the TV show.
 * @property tagLine The tag line of the TV show.
 * @property year The year of the TV show.
 * @property producers The producers of the TV show.
 * @property genres The genres of the TV show.
 * @property networks The networks that aired the TV show.
 * @property seasons The seasons of the TV show.
 * @property totalEpisodes The total number of episodes in the TV show.
 * @property totalSeasons The total number of seasons in the TV show.
 * @property runtime The runtime of the TV show.
 * @property filmType The type of the TV show.
 * @property releaseStatus The release status of the TV show.
 * @property customProperties A map of custom properties associated with the film. Add any properties that your response/resource needs. Also, serialize the value of the property to string.
 *
 * @see FilmMetadata
 * @see Film
 * @see Movie
 * */
@Serializable
data class TvShow(
    override val id: String,
    override val title: String,
    override val posterImage: String?,
    override val homePage: String?,
    override val backdropImage: String? = null,
    override val logoImage: String? = null,
    override val externalIds: Map<FilmIdSource, String> = emptyMap(),
    override val language: String? = null,
    @Serializable(DateAsLongSerializer::class) override val releaseDate: Date? = null,
    override val rating: Double? = null,
    override val producers: List<Company> = emptyList(),
    override val recommendations: List<FilmSearchItem> = emptyList(),
    override val providerId: String,
    override val adult: Boolean = false,
    override val overview: String? = null,
    override val tagLine: String? = null,
    override val genres: List<Genre> = emptyList(),
    override val cast: List<Person> = emptyList(),
    override val customProperties: Map<String, String?> = emptyMap(),

    // == Custom fields ==
    val networks: List<Company> = emptyList(),
    val seasons: List<Season> = emptyList(),
    val totalEpisodes: Int = 0,
    val totalSeasons: Int = 0,
    override val runtime: Int? = null,
) : FilmMetadata() {

    @Deprecated(
        message = "Use sourceIds[FilmIdSource.TMDB]?.toIntOrNull() instead.",
        replaceWith = ReplaceWith("sourceIds[FilmIdSource.TMDB]?.toIntOrNull()"),
    )
    override val tmdbId: Int?
        get() = externalIds[TMDB]?.toIntOrNull()

    @Deprecated(
        message = "Use sourceIds[FilmIdSource.IMDB] instead.",
        replaceWith = ReplaceWith("sourceIds[FilmIdSource.IMDB]"),
    )
    override val imdbId: String?
        get() = externalIds[IMDB]

    @Deprecated(
        message = "Legacy constructor without sourceIds is deprecated. Use sourceIds and Date releaseDate instead.",
        replaceWith = ReplaceWith(
            "TvShow(id, title, posterImage, homePage, backdropImage, logoImage, sourceIds, language = language, releaseDate = releaseDate, rating = rating, producers = producers, recommendations = recommendations, providerId = providerId, adult = adult, overview = overview, tagLine = tagLine, genres = genres, cast = cast, customProperties = customProperties, networks = networks, seasons = seasons, totalEpisodes = totalEpisodes, totalSeasons = totalSeasons, runtime = runtime)"
        ),
        level = DeprecationLevel.WARNING,
    )
    constructor(
        id: String,
        title: String,
        posterImage: String?,
        homePage: String?,
        backdropImage: String? = null,
        logoImage: String? = null,
        tmdbId: Int? = null,
        imdbId: String? = null,
        language: String? = null,
        releaseDate: String? = null,
        rating: Double? = null,
        producers: List<Company> = emptyList(),
        recommendations: List<FilmSearchItem> = emptyList(),
        providerId: String,
        adult: Boolean = false,
        overview: String? = null,
        tagLine: String? = null,
        year: Int? = null,
        genres: List<Genre> = emptyList(),
        cast: List<Person> = emptyList(),
        customProperties: Map<String, String?> = emptyMap(),
        networks: List<Company> = emptyList(),
        seasons: List<Season> = emptyList(),
        totalEpisodes: Int = 0,
        totalSeasons: Int = 0,
        runtime: Int? = null,
    ) : this(
        id = id,
        title = title,
        posterImage = posterImage,
        homePage = homePage,
        backdropImage = backdropImage,
        logoImage = logoImage,
        externalIds = buildMap {
            tmdbId?.let { put(TMDB, it.toString()) }
            imdbId?.let { put(IMDB, it) }
        },
        language = language,
        releaseDate = parseDate(releaseDate) ?: year?.let(::dateFromYear),
        rating = rating,
        producers = producers,
        recommendations = recommendations,
        providerId = providerId,
        adult = adult,
        overview = overview,
        tagLine = tagLine,
        genres = genres,
        cast = cast,
        customProperties = customProperties,
        networks = networks,
        seasons = seasons,
        totalEpisodes = totalEpisodes,
        totalSeasons = totalSeasons,
        runtime = runtime,
    )

    override val filmType: FilmType
        get() = FilmType.TV_SHOW
}
