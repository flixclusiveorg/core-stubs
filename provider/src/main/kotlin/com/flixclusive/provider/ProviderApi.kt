package com.flixclusive.provider

import com.flixclusive.model.film.DEFAULT_FILM_SOURCE_NAME
import com.flixclusive.model.film.Film
import com.flixclusive.model.film.FilmIdSource
import com.flixclusive.model.film.FilmMetadata
import com.flixclusive.model.film.FilmSearchItem
import com.flixclusive.model.film.Movie
import com.flixclusive.model.film.SearchResponseData
import com.flixclusive.model.film.TvShow
import com.flixclusive.model.film.common.tv.Episode
import com.flixclusive.model.provider.ProviderCatalog
import com.flixclusive.model.provider.ProviderCatalogList
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
 * @property catalogs The list of [ProviderCatalog]s that this provider provides. Defaults to an empty list.
 * @property filters The list of [Filter]s that this provider's search method supports. Defaults to an empty list.
 */
@Suppress("unused", "DEPRECATION")
abstract class ProviderApi(
    val provider: ProviderPlugin
) {
    @Deprecated(
        message = "This constructor is deprecated and should not be used. " +
                "Please use the constructor that only takes a ProviderPlugin instance.",
        replaceWith = ReplaceWith("ProviderApi(provider)"),
    )
    constructor(
        client: OkHttpClient,
        provider: Provider,
    ) : this(provider = provider)

    @Deprecated(message = "This property is deprecated and should not be used. ")
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

    @Deprecated(
        message = "Use SearchProviderApi.filters instead.",
        replaceWith = ReplaceWith(
            "(this as SearchProviderApi).filters",
            "com.flixclusive.provider.capability.SearchProviderApi",
        ),
        level = DeprecationLevel.WARNING,
    )
    open val filters: FilterList get() = FilterList()

    @Deprecated(
        message = "Use CatalogProviderApi.catalogs instead.",
        replaceWith = ReplaceWith(
            "(this as CatalogProviderApi).catalogs",
            "com.flixclusive.provider.capability.CatalogProviderApi",
        ),
        level = DeprecationLevel.WARNING,
    )
    open val catalogs: List<ProviderCatalog> get() = emptyList()

    @Deprecated(
        message = "Use CatalogProviderApi.catalogGroups instead.",
        replaceWith = ReplaceWith(
            "(this as CatalogProviderApi).catalogGroups",
            "com.flixclusive.provider.capability.CatalogProviderApi",
        ),
        level = DeprecationLevel.WARNING,
    )
    open val catalogGroups: ProviderCatalogList = ProviderCatalogList()

    @Deprecated(
        message = "Use CrossMatchProviderApi.supportedIdSources instead.",
        replaceWith = ReplaceWith(
            "(this as CrossMatchProviderApi).supportedIdSources",
            "com.flixclusive.provider.capability.CrossMatchProviderApi",
        ),
        level = DeprecationLevel.WARNING,
    )
    open val supportedIdSources: Set<FilmIdSource> get() = emptySet()

    /**
     * Obtains a list of [Film] items from the provider's [catalogs].
     *
     * @param catalog The [ProviderCatalog] to load.
     * @param page The page number for paginated results. Defaults to 1.
     * @return A list of [FilmSearchItem] objects representing the films in the catalog.
     * By default, returns an empty list.
     */
    @Deprecated(
        message = "Use CatalogProviderApi.getCatalogItems instead.",
        replaceWith = ReplaceWith(
            "(this as CatalogProviderApi).getCatalogItems(catalog = catalog, page = page)",
            "com.flixclusive.provider.capability.CatalogProviderApi",
        ),
        level = DeprecationLevel.WARNING,
    )
    open suspend fun getCatalogItems(
        catalog: ProviderCatalog,
        page: Int
    ): SearchResponseData<FilmSearchItem>
        = throw NotImplementedError()

    @Deprecated(
        message = "Use SearchProviderApi.search(title, page, filters) instead. " +
                "ID-based matching should be handled via CrossMatchProviderApi.",
        replaceWith = ReplaceWith(
            "(this as SearchProviderApi).search(title = title, page = page, filters = filters)",
            "com.flixclusive.provider.capability.SearchProviderApi",
        ),
        level = DeprecationLevel.WARNING,
    )
    open suspend fun search(
        title: String,
        page: Int,
        id: String?,
        imdbId: String?,
        tmdbId: Int?,
        filters: FilterList,
    ): SearchResponseData<FilmSearchItem>
        = throw NotImplementedError()

    /**
     * Retrieves detailed information about a film.
     * @param film The [Film] object of the film to retrieve details for.
     * @return a [FilmMetadata] instance containing the film's information. It could either be a [Movie] or [TvShow].
     */
    @Deprecated(
        message = "Use MetadataProviderApi.getMetadata instead.",
        replaceWith = ReplaceWith(
            "(this as MetadataProviderApi).getMetadata(film)",
            "com.flixclusive.provider.capability.MetadataProviderApi",
        ),
        level = DeprecationLevel.WARNING,
    )
    open suspend fun getMetadata(film: Film): FilmMetadata
        = throw NotImplementedError()

    @Deprecated(
        message = "Use StreamProviderApi.getLinks or SubtitleProviderApi.getLinks instead. " +
                "Legacy callback-based getLinks is replaced by Flow-based capability APIs.",
        level = DeprecationLevel.WARNING,
    )
    open suspend fun getLinks(
        watchId: String,
        film: FilmMetadata,
        episode: Episode? = null,
        onLinkFound: (MediaLink) -> Unit,
    ): Unit = throw NotImplementedError()
}