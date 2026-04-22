package com.flixclusive.provider

import com.flixclusive.model.film.DEFAULT_FILM_SOURCE_NAME
import com.flixclusive.model.film.Film
import com.flixclusive.model.film.FilmIdSource
import com.flixclusive.model.film.FilmMetadata
import com.flixclusive.model.film.FilmSearchItem
import com.flixclusive.model.film.Movie
import com.flixclusive.model.film.PaginatedResponse
import com.flixclusive.model.film.TvShow
import com.flixclusive.model.film.common.tv.Episode
import com.flixclusive.model.provider.Catalog
import com.flixclusive.model.provider.link.MediaLink
import com.flixclusive.provider.filter.Filter
import com.flixclusive.provider.filter.FilterList
import okhttp3.OkHttpClient
import java.util.Calendar
import java.util.GregorianCalendar

/**
 * The base class for every provider api.
 *
 * An api will provide content based on configurations. It could also be used to search for films and retrieve detailed information about them.
 *
 * @property provider The [ProviderPlugin] object representing the provider's information.
 *
 * @property baseUrl The base URL used for network requests. Defaults to an empty string.
 * @property testFilm The [Film] to use for testing purposes. Defaults to [The Godfather (1972)](https://www.themoviedb.org/movie/238-the-godfather).
 * @property catalogs The list of [Catalog]s that this provider provides. Defaults to an empty list.
 * @property filters The list of [Filter]s that this provider's search method supports. Defaults to an empty list.
 */
@Deprecated(
    message = "ProviderApi is deprecated. Use capability-specific getters on ProviderPlugin " +
            "(for example, getSearchApi/getCatalogApi/getMetadataApi/getMediaLinkApi) instead.",
    level = DeprecationLevel.WARNING,
)
@Suppress("unused", "DEPRECATION")
abstract class ProviderApi(
    val client: OkHttpClient,
    val provider: ProviderPlugin
) {
    open val baseUrl: String = ""
    open val testFilm: FilmMetadata = Movie(
        id = "238",
        title = "The Godfather",
        rating = 8.691,
        homePage = "http://www.thegodfather.com/",
        releaseDate = GregorianCalendar(1972, Calendar.MARCH, 14).time,
        backdropImage = "https://image.tmdb.org/t/p/w1280/tmU7GeKVybMWFButWEGl2M4GeiP.jpg",
        posterImage = "https://image.tmdb.org/t/p/w500/tmU7GeKVybMWFButWEGl2M4GeiP.jpg",
        providerId = DEFAULT_FILM_SOURCE_NAME,
        overview = "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
    )

    open val filters: FilterList get() = FilterList()

    open val catalogs: List<Catalog> get() = emptyList()

    /**
     * Obtains a list of [Film] items from the provider's [catalogs].
     *
     * @param catalog The [Catalog] to load.
     * @param page The page number for paginated results. Defaults to 1.
     * @return A list of [FilmSearchItem] objects representing the films in the catalog.
     * By default, returns an empty list.
     */
    open suspend fun getCatalogItems(
        catalog: Catalog,
        page: Int
    ): PaginatedResponse<FilmSearchItem>
        = throw NotImplementedError()

    open suspend fun search(
        title: String,
        page: Int,
        id: String?,
        imdbId: String?,
        tmdbId: Int?,
        filters: FilterList,
    ): PaginatedResponse<FilmSearchItem>
        = throw NotImplementedError()

    /**
     * Retrieves detailed information about a film.
     * @param film The [Film] object of the film to retrieve details for.
     * @return a [FilmMetadata] instance containing the film's information. It could either be a [Movie] or [TvShow].
     */
    open suspend fun getMetadata(film: Film): FilmMetadata
        = throw NotImplementedError()

    open suspend fun getLinks(
        watchId: String,
        film: FilmMetadata,
        episode: Episode? = null,
        onLinkFound: (MediaLink) -> Unit,
    ): Unit = throw NotImplementedError()
}