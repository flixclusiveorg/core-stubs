package com.flixclusive.provider.capability.api

import com.flixclusive.model.film.FilmSearchItem
import com.flixclusive.model.film.SearchResponseData
import com.flixclusive.provider.filter.FilterList

/** Capability contract for searching APIs. */
interface SearchProviderApi {
    val filters: FilterList

    /**
     * Canonical search API.
     */
    suspend fun search(
        title: String,
        page: Int = 1,
        filters: FilterList = this.filters,
    ): SearchResponseData<FilmSearchItem>

    @Deprecated(
        message = "ID-based search parameters are deprecated. Use search(title, page, filters) and CrossMatchProviderApi.getById(...) instead.",
        replaceWith = ReplaceWith("search(title = title, page = page, filters = filters)"),
        level = DeprecationLevel.WARNING,
    )
    suspend fun search(
        title: String,
        page: Int,
        id: String?,
        imdbId: String?,
        tmdbId: Int?,
        filters: FilterList,
    ): SearchResponseData<FilmSearchItem> {
        return search(
            title = title,
            page = page,
            filters = filters,
        )
    }
}
