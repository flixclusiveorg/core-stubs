package com.flixclusive.provider

import android.content.Context
import com.flixclusive.model.film.Film
import com.flixclusive.model.film.FilmIdSource
import com.flixclusive.model.film.FilmMetadata
import com.flixclusive.model.film.FilmSearchItem
import com.flixclusive.model.film.Movie
import com.flixclusive.model.film.SearchResponseData
import com.flixclusive.model.film.TvShow
import com.flixclusive.model.film.common.tv.Episode
import com.flixclusive.model.film.util.FilmType
import com.flixclusive.model.provider.ProviderCatalog
import com.flixclusive.model.provider.ProviderCatalogList
import com.flixclusive.model.provider.link.MediaLink
import com.flixclusive.model.provider.link.Stream
import com.flixclusive.model.provider.link.Subtitle
import com.flixclusive.provider.capability.api.CatalogProviderApi
import com.flixclusive.provider.capability.api.CrossMatchProviderApi
import com.flixclusive.provider.capability.api.LinkProviderApi
import com.flixclusive.provider.capability.api.MetadataProviderApi
import com.flixclusive.provider.capability.api.SearchProviderApi
import com.flixclusive.provider.filter.Filter
import com.flixclusive.provider.filter.FilterList
import com.flixclusive.provider.util.DefaultTestFilm.getDefaultTestFilm
import okhttp3.OkHttpClient

/**
 * The base class for every provider api.
 *
 * An api will provide content based on configurations. It could also be used to search for films and retrieve detailed information about them.
 *
 * @property client The dirty [OkHttpClient] instance used for network requests.
 * @property provider The [ProviderPlugin] object representing the provider's information.
 *
 * @property baseUrl The base URL used for network requests. Defaults to an empty string.
 * @property testFilm The [Film] to use for testing purposes. Defaults to [The Godfather (1972)](https://www.themoviedb.org/movie/238-the-godfather).
 * @property catalogs The list of [ProviderCatalog]s that this provider provides. Defaults to an empty list.
 * @property filters The list of [Filter]s that this provider's search method supports. Defaults to an empty list.
 */
@Suppress("unused", "DEPRECATION")
@Deprecated(
    message = "ProviderApi is the legacy all-in-one API manager. Prefer capability-specific APIs exposed by capability provider/factory interfaces.",
    level = DeprecationLevel.WARNING,
)
abstract class ProviderApi(
    val client: OkHttpClient,
    val provider: ProviderPlugin
) : CatalogProviderApi,
    SearchProviderApi,
    MetadataProviderApi,
    CrossMatchProviderApi,
    LinkProviderApi {
    constructor(
        client: OkHttpClient = OkHttpClient()
    ) : this(
        client = client,
        provider = object : ProviderPlugin() {
            @Deprecated(
                message = "Legacy compact API factory. Implement capability provider/factory interfaces with explicit get*Api methods instead.",
                level = DeprecationLevel.WARNING,
            )
            override fun getApi(context: Context, client: OkHttpClient): ProviderApi {
                throw IllegalAccessException("This is a stub class and should not be used directly.")
            }
        }
    )

    open val baseUrl: String = ""
    open val testFilm: FilmMetadata = getDefaultTestFilm()
    override val filters: FilterList get() = FilterList()
    override val catalogs: List<ProviderCatalog> get() = emptyList()
    override val catalogGroups: ProviderCatalogList = ProviderCatalogList()
    override val supportedIdSources: Set<FilmIdSource> get() = emptySet()

    /**
     * Obtains a list of [Film] items from the provider's [catalogs].
     *
     * @param catalog The [ProviderCatalog] to load.
     * @param page The page number for paginated results. Defaults to 1.
     * @return A list of [FilmSearchItem] objects representing the films in the catalog.
     * By default, returns an empty list.
     */
    override suspend fun getCatalogItems(
        catalog: ProviderCatalog,
        page: Int
    ): SearchResponseData<FilmSearchItem>
        = throw NotImplementedError()

    override suspend fun search(
        title: String,
        page: Int,
        filters: FilterList,
    ): SearchResponseData<FilmSearchItem>
        = throw NotImplementedError()

    @Deprecated(
        message = "ID-based search parameters are deprecated. Use search(title, page, filters) and CrossMatchProviderApi.getById(...) instead.",
        replaceWith = ReplaceWith("search(title = title, page = page, filters = filters)"),
        level = DeprecationLevel.WARNING,
    )
    override suspend fun search(
        title: String,
        page: Int,
        id: String?,
        imdbId: String?,
        tmdbId: Int?,
        filters: FilterList,
    ): SearchResponseData<FilmSearchItem>
        = search(
            title = title,
            page = page,
            filters = filters,
        )

    /**
     * Retrieves detailed information about a film.
     * @param film The [Film] object of the film to retrieve details for.
     * @return a [FilmMetadata] instance containing the film's information. It could either be a [Movie] or [TvShow].
     */
    override suspend fun getMetadata(film: Film): FilmMetadata
        = throw NotImplementedError()

    override suspend fun getById(
        sourceIds: Map<FilmIdSource, String>,
        filmType: FilmType?,
        year: Int?,
    ): FilmMetadata? = null

    override suspend fun getByFuzzy(
        title: String,
        sourceIds: Map<FilmIdSource, String>,
        filmType: FilmType?,
        year: Int?,
    ): FilmMetadata? = null

    override suspend fun getLinks(
        film: FilmMetadata,
        episode: Episode?,
        onLinkFound: (MediaLink) -> Unit
    ): Unit = throw NotImplementedError()

    @Deprecated(
        message = "watchId has been removed from getLinks. Use getLinks(film, episode, onLinkFound) instead.",
        replaceWith = ReplaceWith("getLinks(film = film, episode = episode, onLinkFound = onLinkFound)"),
        level = DeprecationLevel.WARNING,
    )
    open suspend fun getLinks(
        watchId: String,
        film: FilmMetadata,
        episode: Episode? = null,
        onLinkFound: (MediaLink) -> Unit,
    ) {
        getLinks(
            film = film,
            episode = episode,
            onLinkFound = onLinkFound,
        )
    }

    open suspend fun getStreams(
        film: FilmMetadata,
        episode: Episode? = null,
        onStreamFound: (Stream) -> Unit
    ) {
        getLinks(
            film = film,
            episode = episode,
        ) { mediaLink ->
            (mediaLink as? Stream)?.let(onStreamFound)
        }
    }

    open suspend fun getSubtitles(
        film: FilmMetadata,
        episode: Episode? = null,
        onSubtitleFound: (Subtitle) -> Unit
    ) {
        getLinks(
            film = film,
            episode = episode,
        ) { mediaLink ->
            (mediaLink as? Subtitle)?.let(onSubtitleFound)
        }
    }
}